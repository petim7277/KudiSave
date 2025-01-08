package com.example.KudiSave.infrastructure.output.persistence.mapper;


import com.example.KudiSave.domain.models.Account;
import com.example.KudiSave.infrastructure.output.persistence.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface AccountPersistenceMapper {
    AccountEntity toAppAccountEntity(Account appAccount);
    Account toAppAccount(AccountEntity appAccountEntity);
}
