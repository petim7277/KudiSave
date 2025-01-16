package com.example.KudiSave.infrastructure.inputs.data.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CreatePasswordRequest {
    private String password;
    private String confirmPassword;

}
