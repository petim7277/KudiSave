package com.example.KudiSave.domain.models;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.keycloak.representations.account.UserRepresentation;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class KudiUser {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt = LocalDateTime.now();
    private UserRepresentation userRepresentation;

}
