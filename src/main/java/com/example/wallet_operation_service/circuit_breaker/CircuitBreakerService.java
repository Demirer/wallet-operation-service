package com.example.wallet_operation_service.circuit_breaker;

import com.example.wallet_operation_service.model.response.TransactionResponse;
import com.example.wallet_operation_service.model.response.WalletOperationServiceResponse;
import org.springframework.http.ResponseEntity;

public class CircuitBreakerService{

    //TODO: COMPLETE AND THINK NEW STRUCTURE
    public static ResponseEntity<WalletOperationServiceResponse<TransactionResponse>> walletDetailsFallback() {
        return null;
    }

    public ResponseEntity<WalletOperationServiceResponse<TransactionResponse>> withdrawalTransactionFallback() {
        return null;
    }

    public ResponseEntity<WalletOperationServiceResponse<TransactionResponse>> creditTransactionFallback() {
        return null;
    }
}
