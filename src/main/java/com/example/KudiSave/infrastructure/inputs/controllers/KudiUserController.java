package com.example.KudiSave.infrastructure.inputs.controllers;
import com.example.KudiSave.application.input.KudiUserManagementUseCase;
import com.example.KudiSave.infrastructure.inputs.data.requests.SignUpRequest;
import com.example.KudiSave.infrastructure.inputs.data.responses.SignUpResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/KudiUserController/")
@AllArgsConstructor
public class KudiUserController {
    private final KudiUserManagementUseCase kudiUserManagementUseCase;
    @PostMapping("/SignUp")
    public ResponseEntity<SignUpResponse> signKudiUserUp (@RequestBody SignUpRequest signUpRequest) {


        return null;
    }
}
