package com.example.KudiSave.repositories;

import com.example.KudiSave.KudiUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


@Component
public interface KudiUserRepository extends MongoRepository<KudiUser, String> {

}
