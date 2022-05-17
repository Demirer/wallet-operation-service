package com.example.wallet_operation_service.controller;

import com.example.wallet_operation_service.constants.Constant;
import com.example.wallet_operation_service.entity.Wallet;
import com.example.wallet_operation_service.model.response.TransactionResponse;
import com.example.wallet_operation_service.model.response.WalletOperationServiceResponse;
import com.example.wallet_operation_service.service.WalletOperation;
import com.example.wallet_operation_service.service.WalletOperationService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/")
@AllArgsConstructor
public class WalletController {

    private final WalletOperationService walletOperationService;

    @PostMapping(value="/wallet-transaction" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WalletOperationServiceResponse<TransactionResponse>> walletTransaction(){
        return ResponseEntity.ok(new WalletOperationServiceResponse<>(new TransactionResponse(), Constant.SUCCESS));
    }

    @GetMapping("/walletDetails/{customerId}")
    public ResponseEntity<WalletOperationServiceResponse<Wallet>> getCustomerWalletDetails (@PathVariable @NotNull Long customerId){
        return ResponseEntity.ok(new WalletOperationServiceResponse<>(walletOperationService.getCustomerWalletDetails(customerId), Constant.SUCCESS));
    }
}
