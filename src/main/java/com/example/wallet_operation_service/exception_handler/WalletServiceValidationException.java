package com.example.wallet_operation_service.exception_handler;

import lombok.Data;

@Data
public class WalletServiceValidationException extends RuntimeException {

    private Long transactionId;

    private Long customerId;

    public WalletServiceValidationException(String exceptionKey,Long transactionId,Long customerId) {
        super(exceptionKey);
        this.transactionId = transactionId;
        this.customerId = customerId;
    }

    public WalletServiceValidationException(String exceptionKey,Long customerId) {
        super(exceptionKey);
        this.customerId = customerId;
    }

    public WalletServiceValidationException(String exceptionKey) {
        super(exceptionKey);
    }

}
