package com.example.wallet_operation_service.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

    @Column(name = "TRANSACTION_AMOUNT")
    private Double transactionAmount;

    @Column(name = "TRANSACTION_DATE")
    private LocalDateTime transactionDateTime;
}
