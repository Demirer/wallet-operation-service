package com.example.wallet_operation_service.validator;

import com.example.wallet_operation_service.entity.TransactionRepository;
import com.example.wallet_operation_service.entity.Wallet;
import com.example.wallet_operation_service.entity.WalletRepository;
import com.example.wallet_operation_service.exception_handler.MessageKey;
import com.example.wallet_operation_service.exception_handler.WalletServiceValidationException;
import com.example.wallet_operation_service.model.request.TransactionRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

/**
 * Validation layer ensure that requests fulfill business and validation requirements. These annotations used in controller layer so if validations
 * not met we are cutting request before enter service layer. We have three annotations implemented in this layer. We are logging every request.
 * These annotations are ;
 * CreditOperationValidation - Checks if customer with given id exists and if given transaction id is unique.
 * WithdrawalOperationValidation - Checks if customer with given id exists, if given transaction id is unique and if there are sufficient balance for withdrawal.
 * WalletDetailsValidation - Checks if customer with given id exists.
 */
@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class ValidationLayer {

    private final WalletRepository walletRepository;

    private final TransactionRepository transactionRepository;

    @Around("@annotation(WithdrawalOperationValidation)")
    public Object withdrawalOperationValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        final TransactionRequest withdrawalOperationRequest = (TransactionRequest) getArguments(joinPoint);
        log.info("Withdrawal request: " + withdrawalOperationRequest + LocalDateTime.now());

        if (!walletRepository.existsById(withdrawalOperationRequest.getCustomerId()))
            throw new WalletServiceValidationException(MessageKey.CUSTOMER_NOT_EXIST);
        if (transactionRepository.existsById(withdrawalOperationRequest.getTransactionId()))
            throw new WalletServiceValidationException(MessageKey.TRANSACTION_ID_UNIQUENESS);
        if (!checkIfFundsAreSufficient(withdrawalOperationRequest.getCustomerId(), withdrawalOperationRequest.getWalletOperationAmount()))
            throw new WalletServiceValidationException(MessageKey.INSUFFICIENT_FUNDS);

        return joinPoint.proceed();
    }

    @Around("@annotation(CreditOperationValidation)")
    public Object creditOperationValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        final TransactionRequest creditOperationRequest = (TransactionRequest) getArguments(joinPoint);
        log.info("Credit request: " + creditOperationRequest + LocalDateTime.now());

        if (!walletRepository.existsById(creditOperationRequest.getCustomerId()))
            throw new WalletServiceValidationException(MessageKey.CUSTOMER_NOT_EXIST);
        if (transactionRepository.existsById(creditOperationRequest.getTransactionId()))
            throw new WalletServiceValidationException(MessageKey.TRANSACTION_ID_UNIQUENESS);

        return joinPoint.proceed();
    }

    @Around("@annotation(WalletDetailsValidation)")
    public Object walletDetailsValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        final Long customerId = (Long) getArguments(joinPoint);
        log.info("Wallet details request: " + customerId + LocalDateTime.now());

        if (!walletRepository.existsById(customerId))
            throw new WalletServiceValidationException(MessageKey.CUSTOMER_NOT_EXIST);

        return joinPoint.proceed();
    }

    private boolean checkIfFundsAreSufficient(Long customerId, Double requestAmountToWithdrawal) {
        Optional<Wallet> wallet = walletRepository.findById(customerId);
        return wallet.filter(value -> value.getCustomerBalance() >= requestAmountToWithdrawal).isPresent();
    }

    private Object getArguments(ProceedingJoinPoint joinPoint) {
        final Optional<Object> arguments = Arrays.stream(joinPoint.getArgs()).findFirst();
        if (arguments.isPresent()) {
            return arguments.get();
        } else {
            throw new WalletServiceValidationException(MessageKey.ARGUMENTS_NOT_FOUND);
        }
    }
}