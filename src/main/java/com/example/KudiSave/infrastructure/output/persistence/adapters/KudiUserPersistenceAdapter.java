package com.example.KudiSave.infrastructure.output.persistence.adapters;

import com.example.KudiSave.application.output.KudiUserPersistencePort;
import com.example.KudiSave.domain.exceptions.ErrorMessages;
import com.example.KudiSave.domain.exceptions.KudiSaveExceptions;
import com.example.KudiSave.domain.models.KudiUser;
import com.example.KudiSave.infrastructure.output.exception.IdentityManagerException;
import com.example.KudiSave.infrastructure.output.persistence.entity.KudiUserEntity;
import com.example.KudiSave.infrastructure.output.persistence.mapper.KudiUserPersistenceMapper;
import com.example.KudiSave.infrastructure.output.persistence.repositories.KudiUserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class KudiUserPersistenceAdapter implements KudiUserPersistencePort {
    private  final KudiUserRepository kudiUserRepository;
    private final   KudiUserPersistenceMapper appUserPersistenceMapper;

    @Override
    public KudiUser saveUser(KudiUser kudiUser) throws KudiSaveExceptions {
        if (kudiUser == null) {
            throw new KudiSaveExceptions(ErrorMessages.KUDI_USER_OBJECT_CANT_BE_EMPTY_OR_NULL,HttpStatus.BAD_REQUEST);
        }
      KudiUserEntity mappedEntity = appUserPersistenceMapper.toKudiUserEntity(kudiUser);
      KudiUserEntity savedEntity = kudiUserRepository.save(mappedEntity);
      return appUserPersistenceMapper.toKudiUser(savedEntity);
    }

    @Override
    public KudiUser findUserById(String userId) throws KudiSaveExceptions {
        if (StringUtils.isNotBlank(userId) || StringUtils.isEmpty(userId)) {
            KudiUserEntity foundEntity = kudiUserRepository.findById(userId)
                    .orElseThrow(()-> new IdentityManagerException(ErrorMessages.KUDI_USER_NOT_FOUND, HttpStatus.NOT_FOUND));
            return appUserPersistenceMapper.toKudiUser(foundEntity);
        }
        throw new KudiSaveExceptions(ErrorMessages.KUDI_USER_OBJECT_CANT_BE_EMPTY_OR_NULL, HttpStatus.BAD_REQUEST);
    }

    @Override
    public KudiUser findUserByEmail(String email) throws KudiSaveExceptions {
        if (StringUtils.isBlank(email) || StringUtils.isEmpty(email)) {
            throw new KudiSaveExceptions(ErrorMessages.KUDI_USER_EMAIL_CANT_BE_EMPTY, HttpStatus.BAD_REQUEST);
        }
        else {
            KudiUserEntity foundEntity = kudiUserRepository.findKudiUserByEmail(email);
            return appUserPersistenceMapper.toKudiUser(foundEntity);
        }
    }
    @Override
    public void deleteUserEntity(KudiUser kudiUser) throws KudiSaveExceptions {
        if (kudiUser == null){ throw new KudiSaveExceptions(
                ErrorMessages.KUDI_USER_OBJECT_CANT_BE_EMPTY_OR_NULL, HttpStatus.BAD_REQUEST);
        }
        KudiUserEntity mappedEntity = appUserPersistenceMapper.toKudiUserEntity(kudiUser);
        kudiUserRepository.delete(mappedEntity);
    }

    @Override
    public KudiUser createPassword(KudiUser kudiUser) {
        if (!kudiUser.getPassword().equals(kudiUser.getConfirmPassword())) {
            throw new KudiSaveExceptions(ErrorMessages.INVALID_PASSWORD_FIELD_ENSURE_CONFIRM_PASSWORD_FIELD_IS_INVALID);
        }
        KudiUserEntity foundUserEntity = kudiUserRepository.findKudiUserByEmail(kudiUser.getEmail());
        if (foundUserEntity == null) {
            throw new KudiSaveExceptions(ErrorMessages.KUDI_USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        if (foundUserEntity.getPassword() != null || foundUserEntity.getConfirmPassword() != null) {
            throw new KudiSaveExceptions(ErrorMessages.PASSWORD_HAS_READY_BEEN_CREATED_FOR_USER,HttpStatus.FORBIDDEN);
        }
        foundUserEntity.setPassword(kudiUser.getPassword());
        foundUserEntity.setConfirmPassword(kudiUser.getConfirmPassword());
        foundUserEntity = kudiUserRepository.save(foundUserEntity);
        return  appUserPersistenceMapper.toKudiUser(foundUserEntity);
    }
}
