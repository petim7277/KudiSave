package com.example.KudiSave.infrastructure.output.persistence.adapters.keycloak;

import com.example.KudiSave.application.output.KudiUserPersistencePort;
import com.example.KudiSave.application.output.keycloak.KudiUserIdentityOutPutPort;
import com.example.KudiSave.domain.exceptions.ErrorMessages;
import com.example.KudiSave.domain.exceptions.KudiSaveExceptions;
import com.example.KudiSave.domain.exceptions.kudi_user_exceptions.KudiUserAlreadyExistException;
import com.example.KudiSave.domain.exceptions.kudi_user_exceptions.KudiUserNotFoundException;
import com.example.KudiSave.domain.models.KudiUser;
import com.example.KudiSave.infrastructure.output.persistence.mapper.KudiUserPersistenceMapper;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.KudiSave.domain.validator.KudiSaveValidator.validateEmail;

@Component
@Slf4j
@RequiredArgsConstructor
public class KeycloakAdapter implements KudiUserIdentityOutPutPort {

    private final Keycloak keycloak;
    private  final KudiUserPersistenceMapper appUserPersistenceMapper;

    @Value("${KEYCLOAK_REALM}")
    private String keycloakRealm;

    @Override
    public KudiUser createUser(KudiUser user) {
       UserRepresentation foundKeyCloakUser = findKeycloakUserByEmail(user.getEmail());
        if (foundKeyCloakUser != null) {
            throw new KudiUserAlreadyExistException(ErrorMessages.KEYCLOAK_USER_ALREADY_EXIST,HttpStatus.CONFLICT);
        }
        UserRepresentation userRepresentation = appUserPersistenceMapper.toUserRepresentation(user);
        userRepresentation.setUsername(user.getEmail());
        userRepresentation.setEnabled(true);
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setFirstName(userRepresentation.getFirstName());
        userRepresentation.setLastName(userRepresentation.getLastName());
        userRepresentation.setCreatedTimestamp(System.currentTimeMillis());
        userRepresentation.setEmailVerified(false);
        Response response = keycloak.realm(keycloakRealm).users().create(userRepresentation);
        if (response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
            log.error(MarkerFactory.getMarker("FATAL"), "Error creating Kudi user with email: {}, Bad request detected, {}",
                    user.getEmail(), response.getStatus());
            throw new KudiSaveExceptions(ErrorMessages.KUDI_USER_CREATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return user;

    }

    @Override
    public void deleteUser(KudiUser user) {
        if (user ==null){throw  new KudiSaveExceptions(ErrorMessages.INVALID_REQUEST,HttpStatus.BAD_REQUEST);}
        UserRepresentation userRepresentation = findKeycloakUserByEmail(user.getEmail());
        if (userRepresentation == null) {
            log.info("Kudi user with email: {} not found", user.getEmail());
            throw  new KudiUserNotFoundException(ErrorMessages.KUDI_KEYCLOAK_USER_NOT_FOUND,HttpStatus.NOT_FOUND);
        }
        UserResource userResource = keycloak.realm(keycloakRealm).users().get(userRepresentation.getId());
        if (userResource == null) {throw new KudiUserNotFoundException(ErrorMessages.KUDI_USER_NOT_FOUND,HttpStatus.NOT_FOUND);}
        userResource.remove();
    }

    @Override
    public UserRepresentation findKeycloakUserByEmail(String email) {
       validateEmail(email);
       List<UserRepresentation> foundUsers = keycloak.realm(keycloakRealm).users().searchByEmail(email, true);
       return foundUsers.isEmpty()? null : foundUsers.getFirst();
    }




}
