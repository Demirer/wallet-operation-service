package com.example.wallet_operation_service.controller;

import com.example.wallet_operation_service.constants.Constant;
import com.example.wallet_operation_service.exception_handler.MessageKey;
import com.example.wallet_operation_service.model.request.TransactionRequest;
import com.example.wallet_operation_service.model.response.TransactionResponse;
import com.example.wallet_operation_service.model.response.WalletDetailResponse;
import com.example.wallet_operation_service.model.response.WalletOperationServiceResponse;
import com.example.wallet_operation_service.service.WalletOperationService;
import com.example.wallet_operation_service.validator.CreditOperationValidation;
import com.example.wallet_operation_service.validator.WithdrawalOperationValidation;
import com.example.wallet_operation_service.validator.WalletDetailsValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("wallet/")
@AllArgsConstructor
public class WalletController {

    private final WalletOperationService walletOperationService;

    @WithdrawalOperationValidation
    @PostMapping(value = "/withdrawal", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WalletOperationServiceResponse<TransactionResponse>> withdrawalOperation(@RequestBody @Valid @NotNull TransactionRequest transactionRequest) {
        return ResponseEntity.ok(new WalletOperationServiceResponse<>(walletOperationService.withdrawalTransaction(transactionRequest), MessageKey.messageExtractor(MessageKey.WITHDRAWAL_SUCCESS_MESSAGE), Constant.SUCCESS));
    }

    @WalletDetailsValidation
    @GetMapping("/walletDetails/{customerId}")
    public ResponseEntity<WalletOperationServiceResponse<WalletDetailResponse>> getCustomerWalletDetails(@PathVariable @NotNull Long customerId) {
        return ResponseEntity.ok(new WalletOperationServiceResponse<>(walletOperationService.walletDetails(customerId), MessageKey.messageExtractor(MessageKey.WALLET_DETAILS_SUCCESS_MESSAGE), Constant.SUCCESS));
    }
}
