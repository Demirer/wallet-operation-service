package com.example.wallet_operation_service.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @param <T>
 * This is response object of out microservice. We are using Java Generics for provide flexibility and type safety.
 */
@Data
@AllArgsConstructor
public class WalletOperationServiceResponse<T> {

    private T responseData;

    private String message;

    private int responseCode;
}
