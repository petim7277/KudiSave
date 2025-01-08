package com.example.KudiSave.application.output;


import com.example.KudiSave.domain.exceptions.KudiSaveExceptions;
import com.example.KudiSave.domain.models.KudiUser;

public interface KudiUserPersistencePort {
    KudiUser saveUser(KudiUser appUser) throws KudiSaveExceptions;
    KudiUser findUserById(String userId) throws KudiSaveExceptions;
    KudiUser findUserByEmail(String email) throws KudiSaveExceptions;
    void deleteUserEntity(KudiUser appUserDomainObject) throws KudiSaveExceptions;
}
