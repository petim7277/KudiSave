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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KudiUserPersistenceAdapter implements KudiUserPersistencePort {
    private  final KudiUserRepository appUserRepository;
    private final   KudiUserPersistenceMapper appUserPersistenceMapper;

    @Override
    public KudiUser saveUser(KudiUser kudiUser) throws KudiSaveExceptions {
        if (kudiUser == null) {
            throw new KudiSaveExceptions(ErrorMessages.KUDI_USER_OBJECT_CANT_BE_EMPTY_OR_NULL,HttpStatus.BAD_REQUEST);
        }
      KudiUserEntity mappedEntity = appUserPersistenceMapper.toKudiUserEntity(kudiUser);
      KudiUserEntity savedEntity = appUserRepository.save(mappedEntity);
      return appUserPersistenceMapper.toKudiUser(savedEntity);
    }

    @Override
    public KudiUser findUserById(String userId) throws KudiSaveExceptions {
        if (StringUtils.isNotBlank(userId) || StringUtils.isEmpty(userId)) {
            KudiUserEntity foundEntity = appUserRepository.findById(userId)
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
            KudiUserEntity foundEntity = appUserRepository.findAppUsersByEmail(email);
            return appUserPersistenceMapper.toKudiUser(foundEntity);
        }
    }
    @Override
    public void deleteUserEntity(KudiUser kudiUser) throws KudiSaveExceptions {
        if (kudiUser == null){ throw new KudiSaveExceptions(
                ErrorMessages.KUDI_USER_OBJECT_CANT_BE_EMPTY_OR_NULL, HttpStatus.BAD_REQUEST);
        }
        KudiUserEntity mappedEntity = appUserPersistenceMapper.toKudiUserEntity(kudiUser);
        appUserRepository.delete(mappedEntity);
    }
}
