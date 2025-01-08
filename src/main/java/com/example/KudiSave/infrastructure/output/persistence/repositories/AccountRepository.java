package com.example.KudiSave.infrastructure.output.persistence.repositories;

import com.example.KudiSave.infrastructure.output.persistence.entity.AccountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<AccountEntity,String> {
}
