package com.example.wallet_operation_service.service;

import com.example.wallet_operation_service.entity.Transaction;
import com.example.wallet_operation_service.entity.TransactionRepository;
import com.example.wallet_operation_service.entity.Wallet;
import com.example.wallet_operation_service.entity.WalletRepository;
import com.example.wallet_operation_service.exception_handler.WalletServiceValidationException;
import com.example.wallet_operation_service.model.request.TransactionRequest;
import com.example.wallet_operation_service.model.response.TransactionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WalletOperationService implements WalletOperation {

    private final WalletRepository walletRepository;

    private final TransactionRepository transactionRepository;

    @Override
    public Wallet getCustomerWalletDetails(Long customerId) {
        Optional<Wallet> walletDetails = walletRepository.findById(customerId);
        if (walletDetails.isPresent()) {
            return walletDetails.get();
        } else {
            throw new WalletServiceValidationException("CUSTOMER NOT EXIST");
        }
    }

    @Override
    public TransactionResponse debitTransaction(TransactionRequest transactionRequest) {
        if(transactionRepository.existsById(transactionRequest.getTransactionId())){
            throw new WalletServiceValidationException("TRANSACTION ID MUST BE UNIQUE");
        }
        if(!checkFundsAreSufficient(transactionRequest.getCustomerId(),transactionRequest.getWalletOperationAmount())){
            throw new WalletServiceValidationException("INSUFFICIENT FUNDS");
        }
        saveTransaction(transactionRequest,"DEBIT");
        walletRepository.updateBalance(-transactionRequest.getWalletOperationAmount(),transactionRequest.getCustomerId());
        return new TransactionResponse(transactionRequest.getTransactionId(),"Successfully REMOVED DEBIT");
    }

    @Override
    public TransactionResponse creditTransaction(TransactionRequest transactionRequest) {
        if(transactionRepository.existsById(transactionRequest.getTransactionId())){
            throw new WalletServiceValidationException("TRANSACTION ID MUST BE UNIQUE");
        }
        saveTransaction(transactionRequest,"CREDIT");
        walletRepository.updateBalance(transactionRequest.getWalletOperationAmount(),transactionRequest.getCustomerId());
        return new TransactionResponse(transactionRequest.getTransactionId(),"Successfully added credit");
    }

    private boolean checkFundsAreSufficient(Long customerId, Double requestAmountToDebit){
        Optional<Wallet> wallet = walletRepository.findById(customerId);
        if (wallet.isPresent()){
            return wallet.get().getBalance()>=requestAmountToDebit;
        }else {
            throw new WalletServiceValidationException("Customer not exist");
        }
    }

    private void saveTransaction(TransactionRequest transactionRequest,String operation){
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
