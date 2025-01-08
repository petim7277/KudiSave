package com.example.KudiSave.infrastructure.inputs.data.requests;

import com.example.KudiSave.domain.models.enums.AccountType;
import lombok.Getter;
import lombok.Setter;
import org.keycloak.representations.idm.UserRepresentation;

@Getter
@Setter
public class SignUpRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phoneNumber;
    private UserRepresentation userRepresentation;
}
