package com.example.wallet_operation_service.service;

import com.example.wallet_operation_service.model.request.TransactionRequest;
import com.example.wallet_operation_service.model.response.TransactionResponse;
import com.example.wallet_operation_service.model.response.WalletDetailResponse;

public interface WalletOperation {

    WalletDetailResponse walletDetails(Long customerId);

    TransactionResponse withdrawalTransaction(TransactionRequest transactionRequest);

    TransactionResponse creditTransaction(TransactionRequest transactionRequest);
}
