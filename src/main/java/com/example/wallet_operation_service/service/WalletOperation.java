package com.example.wallet_operation_service.service;

import com.example.wallet_operation_service.entity.Wallet;
import com.example.wallet_operation_service.model.request.TransactionRequest;
import com.example.wallet_operation_service.model.response.TransactionResponse;

import java.util.Optional;

public interface WalletOperation {

    Wallet getCustomerWalletDetails(Long customerId);

    TransactionResponse debitTransaction(TransactionRequest transactionRequest);

    TransactionResponse creditTransaction(TransactionRequest transactionRequest);
}
