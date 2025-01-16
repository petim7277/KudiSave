package com.example.KudiSave.infrastructure.output.persistence.entity;

import com.example.KudiSave.domain.models.enums.AccountType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;


@Document
@Setter
@Getter
public class KudiUserEntity {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String confirmPassword;
    @Field(write = Field.Write.NON_NULL)
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt = LocalDateTime.now();
}
