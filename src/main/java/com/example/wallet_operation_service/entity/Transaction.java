package com.example.wallet_operation_service.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "WALLET_TRANSACTION")
public class Transaction {

    @Id
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

    @Column(name = "TRANSACTION_AMOUNT")
    private Double balance;

    @Column(name = "TRANSACTION_DATE")
    private LocalDateTime localDateTime;
}
