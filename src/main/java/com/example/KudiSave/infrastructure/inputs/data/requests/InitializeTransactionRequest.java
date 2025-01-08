package com.example.KudiSave.infrastructure.inputs.data.requests;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class InitializeTransactionRequest {
    private String amount;
    private String email;

}
