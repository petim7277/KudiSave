package com.example.KudiSave.domain.services;

import com.example.KudiSave.application.input.TransactionManagementUseCase;
import com.example.KudiSave.infrastructure.config.paystack_config.PaystackConfig;
import com.example.KudiSave.infrastructure.inputs.data.requests.CancelTransactionRequest;
import com.example.KudiSave.infrastructure.inputs.data.requests.InitializeTransactionRequest;
import com.example.KudiSave.infrastructure.inputs.data.requests.TransferMoneyRequest;
import com.example.KudiSave.infrastructure.inputs.data.requests.ViewTransactionHistoryRequest;
import com.example.KudiSave.infrastructure.inputs.data.responses.CancelTransactionResponse;
import com.example.KudiSave.infrastructure.inputs.data.responses.InitializeTransactionResponse;
import com.example.KudiSave.infrastructure.inputs.data.responses.TransferMoneyResponse;
import com.example.KudiSave.infrastructure.inputs.data.responses.ViewTransactionHistoryResponse;
import com.example.KudiSave.infrastructure.output.persistence.entity.KudiUserEntity;
import com.example.KudiSave.infrastructure.output.persistence.repositories.KudiUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class TransactionService implements TransactionManagementUseCase {

    private final PaystackConfig paystackConfig;
    private final KudiUserRepository appUserRepository;


    @Override
    public InitializeTransactionResponse startTransaction(InitializeTransactionRequest transactionRequest) {
        RestTemplate restTemplate = new RestTemplate();
        InitializeTransactionResponse transactionResponse = new InitializeTransactionResponse();
        KudiUserEntity foundUser = appUserRepository.findAppUsersByEmail(transactionRequest.getEmail());
        if (foundUser != null) {
            HttpEntity<InitializeTransactionRequest> request = buildPayment(transactionRequest);
            ResponseEntity<InitializeTransactionResponse>  response = restTemplate
                    .postForEntity(paystackConfig.getPaystackBaseUrl(), request, InitializeTransactionResponse.class);
            return response.getBody();
        }
        transactionResponse.setMessage("Transaction has been successfully started");
        return transactionResponse;
    }

    private HttpEntity<InitializeTransactionRequest> buildPayment(InitializeTransactionRequest initializeTransactionRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION,"Bearer "+paystackConfig.getPaystackApiKey());
        return new HttpEntity<>(initializeTransactionRequest, headers);
    }

    @Override
    public TransferMoneyResponse transferMoney(TransferMoneyRequest transferMoneyRequest) {
        return null;
    }

    @Override
    public BigDecimal getAccountBalance(BigDecimal balance) {
        return null;
    }

    @Override
    public CancelTransactionResponse cancelTransaction(CancelTransactionRequest cancelTransactionRequest) {
        return null;
    }

    @Override
    public CancelTransactionResponse terminateTransaction(CancelTransactionRequest cancelTransactionRequest) {
        return null;
    }

    @Override
    public ViewTransactionHistoryResponse viewTransactionHistory(ViewTransactionHistoryRequest transactionHistoryRequest) {
        return null;
    }



}
