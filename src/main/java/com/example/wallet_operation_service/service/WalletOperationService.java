package com.example.wallet_operation_service.service;

import com.example.wallet_operation_service.entity.Wallet;
import com.example.wallet_operation_service.entity.WalletRepository;
import com.example.wallet_operation_service.exception_handler.WalletServiceValidationException;
import com.example.wallet_operation_service.model.response.TransactionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class WalletOperationService implements WalletOperation {

    private final WalletRepository walletRepository;

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
    public TransactionResponse debitTransaction() {
        return null;
    }

    @Override
    public TransactionResponse creditTransaction() {
        return null;
    }
}
