package com.example.KudiSave.infrastructure.output.persistence.adapters;


import com.example.KudiSave.application.output.AccountPersistencePort;
import com.example.KudiSave.domain.exceptions.ErrorMessages;
import com.example.KudiSave.domain.models.Account;
import com.example.KudiSave.infrastructure.output.exception.IdentityManagerException;
import com.example.KudiSave.infrastructure.output.persistence.entity.AccountEntity;
import com.example.KudiSave.infrastructure.output.persistence.mapper.AccountPersistenceMapper;
import com.example.KudiSave.infrastructure.output.persistence.repositories.AccountRepository;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@AllArgsConstructor
public class AccountPersistenceAdapter implements AccountPersistencePort {

    private final AccountPersistenceMapper appAccountPersistenceMapper;
    private final AccountRepository appAccountRepository;

    @Override
    public Account saveAccount(Account appAccount) {
        AccountEntity mappedEntity = appAccountPersistenceMapper.toAppAccountEntity(appAccount);
        AccountEntity savedAccountEntity =  appAccountRepository.save(mappedEntity);
        return appAccountPersistenceMapper.toAppAccount(savedAccountEntity);
    }

    @Override
    public Optional<Account> findAccountById(String accountId) {
        if (StringUtils.isNotBlank(accountId) || StringUtils.isEmpty(accountId)) {
            AccountEntity foundEntity = appAccountRepository.findById(accountId)
                    .orElseThrow(()-> new IdentityManagerException(ErrorMessages.KUDI_USER_NOT_FOUND, HttpStatus.NOT_FOUND));
            return Optional.ofNullable(appAccountPersistenceMapper.toAppAccount(foundEntity));
        }
        throw new IdentityManagerException(ErrorMessages.KUDI_USER_OBJECT_CANT_BE_EMPTY_OR_NULL, HttpStatus.BAD_REQUEST);
    }

    /**
    This method under normal circumstances should not exist
     as there would not be a need to delete an account where this to be
     a real world fintech project but for the purpose of testing and keeping the database
     clean this method has to exist .
    */
    @Override
    public void deleteAccount(Account appAccount){
        if (appAccount == null) {throw new IdentityManagerException(ErrorMessages.INVALID_REQUEST, HttpStatus.BAD_REQUEST);}
        AccountEntity mappedEntity = appAccountPersistenceMapper.toAppAccountEntity(appAccount);
        appAccountRepository.delete(mappedEntity);
    }
}
