package com.example.KudiSave;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Setter
@Getter
public class KudiUser {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String bvn;
    private String nin;
    private LocalDateTime createdAt = LocalDateTime.now();
//    private UserRepresentation userRepresentation;
//    @Enumerated(EnumType.STRING)
//    private AccountType accountType;
//    private AppAccount appAccount;
}
