package com.example.KudiSave.domain.models;

import com.example.KudiSave.domain.models.enums.AccountType;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

public class Account {

    private  String accountId;
    private  String accountNumber;
    private  BigDecimal accountBalance;
    private AccountType accountType;
    private LocalDateTime createdAt = LocalDateTime.now() ;
    private  LocalDate updatedAt = LocalDate.now();

}
