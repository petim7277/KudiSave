package com.example.KudiSave.infrastructure.output.persistence.repositories;

import com.example.KudiSave.infrastructure.output.persistence.entity.NotificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<NotificationEntity,String> {
}
