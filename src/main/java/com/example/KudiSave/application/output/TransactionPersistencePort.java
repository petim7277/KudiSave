package com.example.KudiSave.application.output;



import com.example.KudiSave.domain.models.Transaction;

import java.util.Optional;

public interface TransactionPersistencePort {
    Transaction saveTransaction(Transaction transaction);
    Optional<Transaction> findTransactionById(Long transactionId);
}
