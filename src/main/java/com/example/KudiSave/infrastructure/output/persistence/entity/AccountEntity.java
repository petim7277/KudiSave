package com.example.KudiSave.infrastructure.output.persistence.entity;


import com.example.KudiSave.domain.models.enums.AccountType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Document
@Getter
@Setter
public class AccountEntity {
    @Id
    private  String accountId;
    private  String accountNumber;
    private BigDecimal accountBalance;
    private AccountType accountType;
    private LocalDateTime createdAt = LocalDateTime.now() ;
    private LocalDate updatedAt = LocalDate.now();
















}
