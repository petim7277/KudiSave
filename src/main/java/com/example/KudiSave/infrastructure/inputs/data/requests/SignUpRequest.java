package com.example.KudiSave.infrastructure.inputs.data.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Valid
@Getter
@Setter
public class SignUpRequest {
    @NotBlank
    @NotNull
    private String firstname;
    @NotBlank
    @NotNull
    private String lastname;
    @NotBlank
    @NotNull
    private String username;
    @NotBlank
    @NotNull
    @Email
    private String email;
    @NotBlank
    @NotNull
    private String phoneNumber;
}
