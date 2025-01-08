package com.example.KudiSave.infrastructure.output.persistence.repositories;

import com.example.KudiSave.infrastructure.output.persistence.entity.KudiUserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KudiUserRepository extends MongoRepository<KudiUserEntity,String> {
    KudiUserEntity findAppUsersByEmail(String email);


}
