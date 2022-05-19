package com.example.wallet_operation_service.exception_handler;

import com.example.wallet_operation_service.model.request.TransactionRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class WalletServiceValidationException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private TransactionRequest transactionRequest;

    private Long customerId;

    public WalletServiceValidationException(String exceptionKey,TransactionRequest transactionRequest) {
        super(exceptionKey);
        this.transactionRequest = transactionRequest;
    }

    public WalletServiceValidationException(String exceptionKey,Long customerId) {
        super(exceptionKey);
        this.customerId = customerId;
    }

    public WalletServiceValidationException(String exceptionKey) {
        super(exceptionKey);
    }
}
