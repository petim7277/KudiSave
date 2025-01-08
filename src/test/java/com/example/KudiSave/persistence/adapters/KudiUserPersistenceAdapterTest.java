package com.example.KudiSave.persistence.adapters;

import com.example.KudiSave.domain.exceptions.KudiSaveExceptions;
import com.example.KudiSave.domain.models.KudiUser;
import com.example.KudiSave.domain.models.enums.AccountType;
import com.example.KudiSave.infrastructure.output.exception.IdentityManagerException;
import com.example.KudiSave.infrastructure.output.persistence.adapters.KudiUserPersistenceAdapter;
import com.example.KudiSave.infrastructure.output.persistence.repositories.KudiUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class KudiUserPersistenceAdapterTest {

    private KudiUser appUser;

    @Autowired
    private KudiUserPersistenceAdapter kudiUserPersistenceAdapter;

    @Autowired
    private KudiUserRepository kudiUserRepository;

    @BeforeEach
    void setUp() {
        appUser = new KudiUser();
        appUser.setFirstname("Nelson");
        appUser.setLastname("Akewe");
        appUser.setUsername("nelly");
        appUser.setPassword("password");
        appUser.setEmail("nelsonakewe@gmail.com");
        appUser.setPhoneNumber("09262280695");
        tearDown();
    }

    @AfterEach
    void tearDown() {
        kudiUserRepository.deleteAll();
    }

    @Test
    void test_SaveUser() {
        KudiUser savedUser = kudiUserPersistenceAdapter.saveUser(appUser);
        assertThat(savedUser.getId(), is(notNullValue()));
        assertNotNull(savedUser);
        assertEquals(1,kudiUserRepository.count());
        log.info("Saved Kudi User === >>>  {}", savedUser);
    }

    @Test
    void test_FindUserById() {
        KudiUser savedUser = kudiUserPersistenceAdapter.saveUser(appUser);
        assertNotNull(savedUser);
        KudiUser user = kudiUserPersistenceAdapter.findUserById(savedUser.getId());
        assertNotNull(user);
    }

    @Test
    void test_FindUserByEmail() {
        KudiUser savedUser = kudiUserPersistenceAdapter.saveUser(appUser);
        assertNotNull(savedUser);
       KudiUser user = kudiUserPersistenceAdapter.findUserByEmail(savedUser.getEmail());
       assertNotNull(user);
    }

    @Test
    void test_FindUserByEmailWith_BlankOrEmptyEmail() {
        appUser.setEmail("");
        KudiUser savedUser = kudiUserPersistenceAdapter.saveUser(appUser);
        assertNotNull(savedUser);
        assertThrows(KudiSaveExceptions.class,()-> kudiUserPersistenceAdapter.findUserByEmail(savedUser.getEmail()));
    }

    @Test
    void test_DeleteUser() {
        KudiUser savedUser = kudiUserPersistenceAdapter.saveUser(appUser);
        assertThat(savedUser.getId(), is(notNullValue()));
        assertNotNull(savedUser);
        kudiUserPersistenceAdapter.deleteUserEntity(savedUser);
        assertEquals(0,kudiUserRepository.count());
        log.info("Deleted Kudi User === >>>  {}", savedUser);

    }


}