package com.example.KudiSave.application.output.keycloak;


import com.example.KudiSave.domain.models.KudiUser;
import org.keycloak.representations.idm.UserRepresentation;

public interface KudiUserIdentityOutPutPort {
    KudiUser createUser(KudiUser appUserDomainObject);
    void deleteUser(KudiUser user);
    UserRepresentation findKeycloakUserByEmail(String email);
}
