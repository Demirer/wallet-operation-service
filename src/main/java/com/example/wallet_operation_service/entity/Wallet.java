package com.example.wallet_operation_service.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "CUSTOMER_ACCOUNT")
public class Wallet {

    @Id
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "CUSTOMER_SURNAME")
    private String customerSurname;

    @Column(name = "CUSTOMER_BALANCE")
    private Double balance;

    @Column(name = "TRANSACTION_HISTORY")
    @OneToMany(mappedBy = "customerId",fetch = FetchType.EAGER)
    private List<Transaction> transactionHistory;
}
