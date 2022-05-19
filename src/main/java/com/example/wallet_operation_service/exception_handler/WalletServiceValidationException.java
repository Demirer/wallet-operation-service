package com.example.wallet_operation_service.exception_handler;

public class WalletServiceValidationException extends RuntimeException {

    public WalletServiceValidationException(String exceptionKey) {
        super(exceptionKey);
    }
}
