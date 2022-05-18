package com.example.wallet_operation_service.model.request;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class TransactionRequest {

    @NotNull
    private Long transactionId;

    @NotNull
    private Long customerId;

    @NotNull
    private Double walletOperationAmount;
}
