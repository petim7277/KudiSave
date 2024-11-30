package com.example.KudiSave.repositories;

import com.example.KudiSave.KudiUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@DataMongoTest
class KudiUserRepositoryTest {

    @Autowired
    private KudiUserRepository kudiUserRepository;

    private KudiUser kudiUser;


    @BeforeEach
    void setUp() {
        kudiUser = new KudiUser();
        kudiUser.setId("1");
        kudiUser.setUsername("kudi");
        kudiUser.setPassword("password");
        kudiUser.setEmail("kudi@gmail.com");
        kudiUser.setBvn("225644891480");
    }

    @Test
    void saveKudiUserTest() {
      KudiUser savedUser = kudiUserRepository.save(kudiUser);
        assertThat(savedUser.getId(), is(notNullValue()));
    }


}