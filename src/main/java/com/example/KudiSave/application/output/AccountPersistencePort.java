package com.example.KudiSave.application.output;


import com.example.KudiSave.domain.models.Account;

import java.util.Optional;

public interface AccountPersistencePort {
    Account saveAccount(Account appAccount);
    Optional<Account> findAccountById(String appAccountId);
    void deleteAccount(Account appAccount);
}
