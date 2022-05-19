package com.example.wallet_operation_service.exception_handler;

import java.io.Serializable;

public class WalletServiceValidationException extends RuntimeException implements Serializable {

    public WalletServiceValidationException(String exceptionKey) {
        super(exceptionKey);
    }
}
