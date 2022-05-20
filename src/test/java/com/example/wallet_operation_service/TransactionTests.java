package com.example.wallet_operation_service;

import com.example.wallet_operation_service.entity.TransactionRepository;
import com.example.wallet_operation_service.entity.Wallet;
import com.example.wallet_operation_service.entity.WalletRepository;
import com.example.wallet_operation_service.model.request.TransactionRequest;
import com.example.wallet_operation_service.model.response.WalletDetailResponse;
import com.example.wallet_operation_service.service.WalletOperationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

@SpringBootTest
class TransactionTests {

    private final WalletRepository walletRepository;

    private final WalletOperationService walletOperationService;

    @Autowired
    TransactionTests(WalletRepository walletRepository,TransactionRepository transactionRepository){
        this.walletRepository =walletRepository;
        this.walletOperationService= new WalletOperationService(walletRepository,transactionRepository);
    }

    @Test
    @DisplayName("Withdrawal operation.")
    void withdrawalOperation() {
        Optional<Wallet> initialBalanceBeforeWithdrawal =  walletRepository.findById(1L);
        if(initialBalanceBeforeWithdrawal.isPresent()){
            TransactionRequest transactionRequest = new TransactionRequest(12462941909113L, 1L, 250.0);
            walletOperationService.withdrawalTransaction(transactionRequest);
            Assertions.assertEquals(initialBalanceBeforeWithdrawal.get().getCustomerBalance() -  250.0, walletOperationService.walletDetails(1L).getWallet().getCustomerBalance());
        }
    }


    @Test
    @DisplayName("Credit operation.")
    void creditOperation() {
        Optional<Wallet> initialBalanceBeforeCredit =  walletRepository.findById(1L);
        if(initialBalanceBeforeCredit.isPresent()){
            TransactionRequest transactionRequest = new TransactionRequest(92729419013L, 1L, 500.0);
            walletOperationService.creditTransaction(transactionRequest);
            Assertions.assertEquals(initialBalanceBeforeCredit.get().getCustomerBalance() + 500.0, walletOperationService.walletDetails(1L).getWallet().getCustomerBalance());
        }
    }

    @Test
    @DisplayName("Wallet details operation.")
    void getCustomerWalletDetailsOperation() {
        Optional<Wallet> customerWallet =  walletRepository.findById(1L);
        if(customerWallet.isPresent()){
            WalletDetailResponse walletDetailResponse = walletOperationService.walletDetails(1L);
            Assertions.assertEquals(customerWallet.get().getCustomerId(),walletDetailResponse.getWallet().getCustomerId());
            Assertions.assertEquals(customerWallet.get().getCustomerName(),walletDetailResponse.getWallet().getCustomerName());
            Assertions.assertEquals(customerWallet.get().getCustomerSurname(),walletDetailResponse.getWallet().getCustomerSurname());
            Assertions.assertEquals(customerWallet.get().getCustomerBalance(),walletDetailResponse.getWallet().getCustomerBalance());
            Assertions.assertEquals(customerWallet.get().getTransactionHistory().size(),walletDetailResponse.getWallet().getTransactionHistory().size());
        }
    }

}
