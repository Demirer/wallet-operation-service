package com.example.wallet_operation_service.entity;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * We are processing only transaction in this microservice. Each transaction has transaction id and it is key.
 * Also, transaction entity has customer id which shows transaction owner customer.
 * Transactions have two types, Credit OR Withdrawal. We record transaction date time properly as well.
 * Rework DB
 */
@Data
@Entity
@Builder
@AllArgsConstructor
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
