package com.example.KudiSave.infrastructure.inputs.controllers;
import com.example.KudiSave.application.input.KudiUserManagementUseCase;
import com.example.KudiSave.domain.exceptions.ErrorMessages;
import com.example.KudiSave.domain.exceptions.KudiSaveExceptions;
import com.example.KudiSave.domain.models.KudiUser;
import com.example.KudiSave.infrastructure.inputs.data.requests.SignUpRequest;
import com.example.KudiSave.infrastructure.inputs.data.responses.SignUpResponse;
import com.example.KudiSave.infrastructure.inputs.mapper.KudiUserRestMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/v1/KudiUserController/")
@AllArgsConstructor
public class KudiUserController {
    private final KudiUserManagementUseCase kudiUserManagementUseCase;

    private final KudiUserRestMapper kudiUserRestMapper;

    @PostMapping("/SignUp")
    public ResponseEntity<SignUpResponse> signKudiUserUp (@RequestBody SignUpRequest signUpRequest) {
        log.info("SIGNUP REQUEST:::==>>  {}", signUpRequest);
        KudiUser mappedRequest = kudiUserRestMapper.toKudiUser(signUpRequest);
        KudiUser signedUpUser = kudiUserManagementUseCase.signUp(mappedRequest);
        SignUpResponse signUpResponse = SignUpResponse.builder()
                .email(signedUpUser.getEmail())
                .firstname(signedUpUser.getFirstname())
                .lastname(signedUpUser.getLastname())
                .phoneNumber(signedUpUser.getPhoneNumber())
                .username(signedUpUser.getUsername())
                .message("Kudi user successfully signed in. ").build();
        log.info("SIGNUP RESPONSE:::==>>  {}", signUpResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpResponse);
    }
}
