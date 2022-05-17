package com.example.wallet_operation_service.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WalletOperationServiceResponse<T> {

    private T responseData;

    private int responseCode;
}
