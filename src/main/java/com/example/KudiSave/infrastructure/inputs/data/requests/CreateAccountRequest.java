package com.example.KudiSave.infrastructure.inputs.data.requests;
import com.example.KudiSave.domain.models.enums.AccountType;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CreateAccountRequest {
    private AccountType accountType;
}
