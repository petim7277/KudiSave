package com.example.KudiSave.application.input;

import com.example.KudiSave.infrastructure.inputs.data.requests.CancelTransactionRequest;
import com.example.KudiSave.infrastructure.inputs.data.requests.InitializeTransactionRequest;
import com.example.KudiSave.infrastructure.inputs.data.requests.TransferMoneyRequest;
import com.example.KudiSave.infrastructure.inputs.data.requests.ViewTransactionHistoryRequest;
import com.example.KudiSave.infrastructure.inputs.data.responses.CancelTransactionResponse;
import com.example.KudiSave.infrastructure.inputs.data.responses.InitializeTransactionResponse;
import com.example.KudiSave.infrastructure.inputs.data.responses.TransferMoneyResponse;
import com.example.KudiSave.infrastructure.inputs.data.responses.ViewTransactionHistoryResponse;

import java.math.BigDecimal;

public interface TransactionManagementUseCase {
    InitializeTransactionResponse startTransaction(InitializeTransactionRequest initializeTransactionRequest);
    TransferMoneyResponse transferMoney(TransferMoneyRequest transferMoneyRequest);
    BigDecimal getAccountBalance (BigDecimal balance);
    CancelTransactionResponse cancelTransaction (CancelTransactionRequest cancelTransactionRequest);
    CancelTransactionResponse terminateTransaction(CancelTransactionRequest cancelTransactionRequest);
    ViewTransactionHistoryResponse viewTransactionHistory (ViewTransactionHistoryRequest transactionHistoryRequest);

}
