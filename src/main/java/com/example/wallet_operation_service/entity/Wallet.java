package com.example.wallet_operation_service.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

/**
 * This is out wallet entity  Each wallet has ONLY one owner.(customer id)
 * We are using ORM and properly fetching all transactions which belongs to wallet with customerId column.
 * Transaction list always in ordered according to transaction date.
 */
@Data
@Entity
@Table(name = "WALLET")
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
    @OrderBy("transactionDateTime")
    @OneToMany(mappedBy = "customerId", fetch = FetchType.EAGER)
    private List<Transaction> transactionHistory;
}
