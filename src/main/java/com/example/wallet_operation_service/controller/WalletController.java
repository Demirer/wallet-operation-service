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

/**
 * This is controller layer of our wallet microservice. As best practice, each service designed atomic and independent.
 * We are our own custom annotations for check business rules and validations. (For example : transaction id must be unique as written in analysis document)
 * System architecture implemented with validation first design, this means that we are directly cutting request which are not valid.
 * We are not allowing them to enter service layer. This provides dramatically load reduce on services for INVALID requests (Both technical & business rules).
 */
@RestController
@RequestMapping("wallet/")
@AllArgsConstructor
public class WalletController {

    private final WalletOperationService walletOperationService;

    @CreditOperationValidation
    @PostMapping(value = "/credit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WalletOperationServiceResponse<TransactionResponse>> creditOperation(@RequestBody @Valid @NotNull TransactionRequest transactionRequest) {
        return ResponseEntity.ok(new WalletOperationServiceResponse<>(walletOperationService.creditTransaction(transactionRequest), MessageKey.messageExtractor(MessageKey.CREDIT_SUCCESS_MESSAGE), Constant.SUCCESS));
    }

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
