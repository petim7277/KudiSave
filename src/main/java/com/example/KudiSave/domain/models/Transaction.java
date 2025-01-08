package com.example.KudiSave.domain.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public class Transaction {
    @Id
 private String transactionId;
 private LocalDateTime transactionDate;
 private String description;

}
