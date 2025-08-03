package com.example.wallet_operation_service.service;

import com.example.wallet_operation_service.constants.Constant;
import com.example.wallet_operation_service.entity.Transaction;
import com.example.wallet_operation_service.entity.TransactionRepository;
import com.example.wallet_operation_service.entity.WalletRepository;
import com.example.wallet_operation_service.model.request.TransactionRequest;
import com.example.wallet_operation_service.model.response.TransactionResponse;
import com.example.wallet_operation_service.model.response.WalletDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class WalletOperationService implements WalletOperation {

    private final WalletRepository walletRepository;

    private final TransactionRepository transactionRepository;

    @Override
    public WalletDetailResponse walletDetails(Long customerId) {
        WalletDetailResponse walletDetailResponse = new WalletDetailResponse();
        walletRepository.findById(customerId).ifPresent(walletDetailResponse::setWallet);
        return walletDetailResponse;
    }

    @Override
    public TransactionResponse creditTransaction(TransactionRequest transactionRequest) {
        processTransaction(transactionRequest,Constant.CREDIT_OPERATION);
        walletRepository.updateBalance(transactionRequest.getWalletOperationAmount(),transactionRequest.getCustomerId());
        return new TransactionResponse(transactionRequest.getTransactionId());
    }

    private void processTransaction(TransactionRequest transactionRequest, String operation) {
        Transaction transaction = Transaction.builder()
                .transactionId(transactionRequest.getTransactionId())
                .customerId(transactionRequest.getCustomerId())
                .transactionType(operation)
                .transactionAmount(transactionRequest.getWalletOperationAmount())
                .transactionDateTime(LocalDateTime.now())
                .build();
        transactionRepository.save(transaction);
    }
}
