package com.example.KudiSave.persistence.repositories;

import com.example.KudiSave.infrastructure.output.persistence.entity.KudiUserEntity;
import com.example.KudiSave.infrastructure.output.persistence.repositories.KudiUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


@DataMongoTest
public class AppUserRepositoryTest {

    @Autowired
    private KudiUserRepository appUserRepository;


    @Test
    public void saveUserTest(){
        KudiUserEntity appUser = new KudiUserEntity();
        var savedUser = appUserRepository.save(appUser);
        assertThat(savedUser.getId(), is(notNullValue()));
    }

    @Test
    public void saveUserCountIsOneTest(){
        KudiUserEntity appUser = new KudiUserEntity();
        var savedUser = appUserRepository.save(appUser);
        assertThat(appUserRepository.count(), is(equalTo(1L)));
    }

    @Test
    public void saveUserCountIsOneAgainTest(){
        KudiUserEntity appUser = new KudiUserEntity();
        var savedUser = appUserRepository.save(appUser);
        assertThat(appUserRepository.count(), is(equalTo(1L)));
    }

}