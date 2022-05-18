package com.example.wallet_operation_service.exception_handler;

import com.example.wallet_operation_service.constants.Constant;
import com.example.wallet_operation_service.model.response.TransactionResponse;
import com.example.wallet_operation_service.model.response.WalletOperationServiceResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ResourceBundle;

@Slf4j
@Component
@ControllerAdvice
@AllArgsConstructor
public class WalletOperationServiceExceptionHandler {

    @ExceptionHandler(value = WalletServiceValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<WalletOperationServiceResponse<String>> walletServiceValidationException(WalletServiceValidationException walletServiceValidationException){

        final String exceptionMessage = MessageKey.messageExtractor(walletServiceValidationException.getLocalizedMessage());

        log.error("Validation exception occurred : " + exceptionMessage + " at time : " + LocalDateTime.now() + " Customer Id: "
                + walletServiceValidationException.getCustomerId() + " TransactionId : " + walletServiceValidationException.getTransactionId()
                + "Exception: " + walletServiceValidationException);

        return new ResponseEntity<>(new WalletOperationServiceResponse<>(exceptionMessage,Constant.FAILURE),HttpStatus.BAD_REQUEST);
    }
}
