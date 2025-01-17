package com.example.KudiSave.domain.services;


import com.example.KudiSave.application.input.KudiUserManagementUseCase;
import com.example.KudiSave.application.output.KudiUserPersistencePort;
import com.example.KudiSave.application.output.keycloak.KudiUserIdentityOutPutPort;
import com.example.KudiSave.domain.exceptions.ErrorMessages;
import com.example.KudiSave.domain.exceptions.KudiSaveExceptions;
import com.example.KudiSave.domain.exceptions.kudi_user_exceptions.KudiUserAlreadyExistException;
import com.example.KudiSave.domain.exceptions.kudi_user_exceptions.KudiUserNotFoundException;
import com.example.KudiSave.domain.mapper.KudiSaveServiceMapper;
import com.example.KudiSave.domain.models.KudiUser;
import com.example.KudiSave.infrastructure.inputs.data.requests.*;
import com.example.KudiSave.infrastructure.inputs.data.responses.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import static com.example.KudiSave.domain.validator.KudiSaveValidator.*;

@Slf4j
@AllArgsConstructor
@Service
public class KudiUserService implements KudiUserManagementUseCase {
    private final KudiUserPersistencePort userPersistenceOutputPort;
    private final KudiUserIdentityOutPutPort kudiUserIdentityManagerOutPutPort;
    private final KudiSaveServiceMapper kudiSaveServiceMapper;

    @Override
    public KudiUser signUp(KudiUser user)throws KudiSaveExceptions {
        if (user == null) { throw new KudiSaveExceptions(ErrorMessages.KUDI_USER_OBJECT_CANT_BE_EMPTY_OR_NULL,HttpStatus.BAD_REQUEST); }
        validateUserFields(user);
        KudiUser foundUser = userPersistenceOutputPort.findUserByEmail(user.getEmail());
        if (foundUser != null) {
            throw  new KudiUserAlreadyExistException(ErrorMessages.KUDI_USER_ALREADY_EXIST,HttpStatus.BAD_REQUEST);
        }
        try {
            KudiUser createdUser = userPersistenceOutputPort.saveUser(user);
            log.info(" Created user::==> {}", createdUser);
            KudiUser createdKeycloakUser = kudiUserIdentityManagerOutPutPort.createUser(createdUser);
            log.info("Created keycloak user::==> {}", createdKeycloakUser);
            if (createdKeycloakUser == null) {
                throw new KudiSaveExceptions(ErrorMessages.KUDI_USER_CREATION_FAILED,HttpStatus.BAD_REQUEST);
            }
            return createdUser;
        }catch (Exception ex) {
            log.error("Error while creating Keycloak user: {}", ex.getMessage(), ex);
            throw new KudiSaveExceptions(
                    ErrorMessages.KUDI_USER_CREATION_FAILED,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }


    @Override
    public String createPassword(KudiUser kudiUser) throws KudiSaveExceptions {
        try {
            validatePassword(kudiUser.getPassword());
            if (!kudiUser.getPassword().equals(kudiUser.getConfirmPassword())) {
                throw  new KudiSaveExceptions(ErrorMessages.INVALID_PASSWORD_FIELD_ENSURE_CONFIRM_PASSWORD_FIELD_IS_INVALID,HttpStatus.BAD_REQUEST);
            }
            KudiUser createdUserPassword = userPersistenceOutputPort.createPassword(kudiUser);
            return  "User "+createdUserPassword.getFirstname()+" "+createdUserPassword.getLastname()+" has successfully created a password";

        } catch (KudiSaveExceptions e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String signIn(KudiUser userDomainObject) throws KudiSaveExceptions {
        KudiUser foundUser = userPersistenceOutputPort.findUserByEmail(userDomainObject.getEmail());
        if (foundUser== null){
            throw  new KudiUserNotFoundException(ErrorMessages.KUDI_USER_NOT_FOUND,HttpStatus.NOT_FOUND);
        }
        if (foundUser.getPassword() == null){
            throw  new KudiUserNotFoundException(ErrorMessages.KUDI_USER_NOT_FOUND,HttpStatus.NOT_FOUND);

        }
        if (kudiUserIdentityManagerOutPutPort.findKeycloakUserByEmail(foundUser.getEmail()) == null) {
            throw new KudiUserNotFoundException(ErrorMessages.KUDI_KEYCLOAK_USER_NOT_FOUND,HttpStatus.NOT_FOUND);
        }
        return "User with email"+foundUser.getEmail()+" has been logged in  and username"+foundUser.getUsername();
    }

//Still working on signIn method

    @Override
    public RecieveMoneyResponse recieveMoney(RecieveMoneyRequest recieveMoneyRequest) {
        return null;
    }



    @Override
    public ViewUserDetailsResponse viewUserProfile(ViewUserDetailsRequest viewUserDetailsRequest) {
        return null;
    }

    public void validateUserFields(KudiUser user){
        validateName(user.getUsername());
        validateEmail(user.getEmail());
        validatePhoneNumber(user.getPhoneNumber());
    }

}
