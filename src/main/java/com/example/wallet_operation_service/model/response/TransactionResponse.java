package com.example.wallet_operation_service.model.response;

import lombok.Data;

@Data
public class TransactionResponse {

    private Long transactionId;
    private String message;
}
