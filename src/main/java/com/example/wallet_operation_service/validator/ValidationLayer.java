package com.example.wallet_operation_service.validator;

import com.example.wallet_operation_service.entity.TransactionRepository;
import com.example.wallet_operation_service.entity.Wallet;
import com.example.wallet_operation_service.entity.WalletRepository;
import com.example.wallet_operation_service.exception_handler.MessageKey;
import com.example.wallet_operation_service.exception_handler.WalletServiceValidationException;
import com.example.wallet_operation_service.model.request.TransactionRequest;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
@AllArgsConstructor
public class ValidationLayer {

    private final WalletRepository walletRepository;

    private final TransactionRepository transactionRepository;

    @Around("@annotation(com.example.wallet_operation_service.validator.WithdrawalOperationValidation)")
    public Object debitOperationValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        final TransactionRequest debitOperationRequest = (TransactionRequest) getArguments(joinPoint);

        if (!walletRepository.existsById(debitOperationRequest.getCustomerId()))
            throw new WalletServiceValidationException(MessageKey.CUSTOMER_NOT_EXIST);
        if (transactionRepository.existsById(debitOperationRequest.getTransactionId()))
            throw new WalletServiceValidationException(MessageKey.TRANSACTION_ID_UNIQUENESS);
        if (!checkIfFundsAreSufficient(debitOperationRequest.getCustomerId(), debitOperationRequest.getWalletOperationAmount()))
            throw new WalletServiceValidationException(MessageKey.INSUFFICIENT_FUNDS);

        return joinPoint.proceed();
    }

    @Around("@annotation(CreditOperationValidation)")
    public Object creditOperationValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        final TransactionRequest creditOperationRequest = (TransactionRequest) getArguments(joinPoint);

        if (!walletRepository.existsById(creditOperationRequest.getCustomerId()))
            throw new WalletServiceValidationException(MessageKey.CUSTOMER_NOT_EXIST);
        if (transactionRepository.existsById(creditOperationRequest.getTransactionId()))
            throw new WalletServiceValidationException(MessageKey.TRANSACTION_ID_UNIQUENESS);

        return joinPoint.proceed();
    }

    @Around("@annotation(WalletDetailsValidation)")
    public Object walletDetailsValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        final Long customerId = (Long) getArguments(joinPoint);

        if (!walletRepository.existsById(customerId))
            throw new WalletServiceValidationException(MessageKey.CUSTOMER_NOT_EXIST);

        return joinPoint.proceed();
    }

    private boolean checkIfFundsAreSufficient(Long customerId, Double requestAmountToDebit) {
        Optional<Wallet> wallet = walletRepository.findById(customerId);
        return wallet.filter(value -> value.getBalance() >= requestAmountToDebit).isPresent();
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