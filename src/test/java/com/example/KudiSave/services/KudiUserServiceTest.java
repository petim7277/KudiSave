package com.example.KudiSave.services;



import com.example.KudiSave.application.output.KudiUserPersistencePort;
import com.example.KudiSave.application.output.keycloak.KudiUserIdentityOutPutPort;
import com.example.KudiSave.domain.exceptions.kudi_user_exceptions.InvalidInputFieldsException;
import com.example.KudiSave.domain.exceptions.kudi_user_exceptions.KudiUserAlreadyExistException;
import com.example.KudiSave.domain.models.KudiUser;
import com.example.KudiSave.domain.models.enums.AccountType;
import com.example.KudiSave.domain.services.KudiUserService;
import com.example.KudiSave.infrastructure.output.persistence.repositories.KudiUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
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
        user.setPassword("Password123!");
        user.setEmail("joanwinter@gmail.com");
        user.setFirstname("Joan");
        user.setLastname("Winter");
        user.setPhoneNumber("08162380765");

    }

    @Test
    void signUpTest() {
       KudiUser signedUpUser =  kudiUserService.signUp(user);
       assertNotNull(signedUpUser);
       assertEquals(user.getUsername(), signedUpUser.getUsername());
        kudiUserIdentityManagerOutPutPort.deleteUser(signedUpUser);
        kudiUserPersistencePort.deleteUserEntity(signedUpUser);
    }

    @Test
    void testThatAnExisting_UserCannotSignUp() {
        KudiUser signedUpUser =  kudiUserService.signUp(user);
        assertNotNull(signedUpUser);
        assertThrows(KudiUserAlreadyExistException.class,()-> kudiUserService.signUp(signedUpUser));
        kudiUserIdentityManagerOutPutPort.deleteUser(signedUpUser);
        kudiUserPersistencePort.deleteUserEntity(signedUpUser);
    }

   @Test
    void testThatKudiUserCan_SignUpWith_EmptyUsername_Field() {
        user.setUsername("");
        assertThrows(InvalidInputFieldsException.class,()-> kudiUserService.signUp(user));
   }

   @Test
    void testThatKudiUserCan_SignUpWith_WrongUsername_Field() {
        user.setUsername("Pressy_123+");
        assertThrows(InvalidInputFieldsException.class,()-> kudiUserService.signUp(user));
    }

    @Test
    void testThatKudiUserCan_SignUpWith_EmptyEmail_Field() {
        user.setEmail("");
        assertThrows(InvalidInputFieldsException.class,()-> kudiUserService.signUp(user));
    }

    @Test
    void testThatKudiUserCan_SignUpWith_WrongEmail_Field() {
        user.setEmail("petim@gmailcom");
        assertThrows(InvalidInputFieldsException.class,()-> kudiUserService.signUp(user));
    }


    @Test
    void testThatKudiUserCan_SignUpWith_EmptyPassword_Field() {
        user.setPassword("");
        assertThrows(InvalidInputFieldsException.class,()-> kudiUserService.signUp(user));
    }

    @Test
    void testThatKudiUserCan_SignUpWith_WrongPassword_Field() {
        user.setPassword("password123!");
        assertThrows(InvalidInputFieldsException.class,()-> kudiUserService.signUp(user));
    }


    @Test
    void testThatKudiUserCan_SignUpWith_EmptyPhoneNumber_Field() {
        user.setPhoneNumber("");
        assertThrows(InvalidInputFieldsException.class,()-> kudiUserService.signUp(user));
    }

    @Test
    void testThatKudiUserCan_SignUpWith_WrongPhoneNumber_Field() {
        user.setPhoneNumber("0916228079A");
        assertThrows(InvalidInputFieldsException.class,()-> kudiUserService.signUp(user));
    }

//    still need to correct these test cases

//    @Test
//    void testAKudiUser_CanSignIn_WithoutSigningUpFirst(){
//        AppUser userDomainObject = new AppUser();
//        userDomainObject.setEmail("jackiewinter@gmail.com");
//        assertThrows(KudiUserNotFoundException.class,()-> kudiUserService.signIn(userDomainObject));
//    }
//
//    @Test
//    void testAKudiUser_WhoHasSignedUp_CanSignIn(){
//        AppUser userDomainObject = new AppUser();
//        userDomainObject.setUsername("sandra123");
//        userDomainObject.setPassword("Password123!");
//        userDomainObject.setEmail("sandrapeters@gmail.com");
//        userDomainObject.setFirstname("Sandra");
//        userDomainObject.setLastname("Peters");
//        userDomainObject.setPhoneNumber("08162380765");
//        userDomainObject.setAccountType(AccountType.SAVINGS);
//
//       AppUser signedUpUser =  kudiUserService.signUp(userDomainObject);
//       assertNotNull(signedUpUser);
//
//       AppUser signedInUser =  kudiUserService.signIn(signedUpUser);
//       assertNotNull(signedInUser);
//
//       kudiUserIdentityManagerOutPutPort.deleteUser(signedUpUser);
//    }

}