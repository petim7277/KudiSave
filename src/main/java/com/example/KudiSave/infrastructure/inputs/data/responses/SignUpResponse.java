package com.example.KudiSave.infrastructure.inputs.data.responses;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class SignUpResponse {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phoneNumber;
    private  String message;

}
