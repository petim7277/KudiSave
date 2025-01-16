package com.example.KudiSave.services;

import com.example.KudiSave.application.output.KudiUserPersistencePort;
import com.example.KudiSave.application.output.keycloak.KudiUserIdentityOutPutPort;
import com.example.KudiSave.domain.exceptions.kudi_user_exceptions.InvalidInputFieldsException;
import com.example.KudiSave.domain.exceptions.kudi_user_exceptions.KudiUserAlreadyExistException;
import com.example.KudiSave.domain.models.KudiUser;
import com.example.KudiSave.domain.services.KudiUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class KudiUserServiceTest {
    @Autowired
    private KudiUserService kudiUserService;

    @Autowired
    private KudiUserIdentityOutPutPort kudiUserIdentityManagerOutPutPort;

    @Autowired
    private KudiUserPersistencePort kudiUserPersistencePort;

    private KudiUser user;

    @BeforeEach
    void setUp() {
        user = new KudiUser();
        user.setUsername("joan123");
        user.setEmail("joanwinter@gmail.com");
        user.setFirstname("Joan");
        user.setLastname("Winter");
        user.setPhoneNumber("08162380765");
    }


    @Test
    void signUpTest() {
        KudiUser signedUpUser = kudiUserService.signUp(user);
        assertNotNull(signedUpUser);
        assertEquals(user.getUsername(), signedUpUser.getUsername());
        kudiUserIdentityManagerOutPutPort.deleteUser(signedUpUser);
        kudiUserPersistencePort.deleteUserEntity(signedUpUser);
    }

    @Test
    void testThatAnExisting_UserCannotSignUp() {
        KudiUser signedUpUser = kudiUserService.signUp(user);
        assertNotNull(signedUpUser);
        assertThrows(KudiUserAlreadyExistException.class, () -> kudiUserService.signUp(signedUpUser));
        kudiUserIdentityManagerOutPutPort.deleteUser(signedUpUser);
        kudiUserPersistencePort.deleteUserEntity(signedUpUser);
    }

    @Test
    void testThatKudiUserCan_SignUpWith_EmptyUsername_Field() {
        user.setUsername("");
        assertThrows(InvalidInputFieldsException.class, () -> kudiUserService.signUp(user));
    }

    @Test
    void testThatKudiUserCan_SignUpWith_WrongUsername_Field() {
        user.setUsername("Pressy_123+");
        assertThrows(InvalidInputFieldsException.class, () -> kudiUserService.signUp(user));
    }

    @Test
    void testThatKudiUserCan_SignUpWith_EmptyEmail_Field() {
        user.setEmail("");
        assertThrows(InvalidInputFieldsException.class, () -> kudiUserService.signUp(user));
    }

    @Test
    void testThatKudiUserCan_SignUpWith_WrongEmail_Field() {
        user.setEmail("petim@gmailcom");
        assertThrows(InvalidInputFieldsException.class, () -> kudiUserService.signUp(user));
    }


    @Test
    void testThatKudiUserCan_SignUpWith_EmptyPhoneNumber_Field() {
        user.setPhoneNumber("");
        assertThrows(InvalidInputFieldsException.class, () -> kudiUserService.signUp(user));
    }

    @Test
    void testThatKudiUserCan_SignUpWith_WrongPhoneNumber_Field() {
        user.setPhoneNumber("0916228079A");
        assertThrows(InvalidInputFieldsException.class, () -> kudiUserService.signUp(user));
    }

    @Test
    void testCreatePasswordSuccessfully() {

        KudiUser signedUpUser = kudiUserService.signUp(user);
        assertNotNull(signedUpUser);
        signedUpUser.setPassword("@ValidPassword123");
        signedUpUser.setConfirmPassword("@ValidPassword123");
        String createdUserPassword = kudiUserService.createPassword(signedUpUser);
        log.info(createdUserPassword);
        kudiUserIdentityManagerOutPutPort.deleteUser(signedUpUser);
        kudiUserPersistencePort.deleteUserEntity(signedUpUser);
    }


}