package com.example.wallet_operation_service.exception_handler;

import com.example.wallet_operation_service.constants.Constant;
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
import java.util.Arrays;

@Slf4j
@Component
@ControllerAdvice
@AllArgsConstructor
public class WalletOperationServiceExceptionHandler {

    @ExceptionHandler(value = WalletServiceValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<WalletOperationServiceResponse<String>> walletServiceValidationException(WalletServiceValidationException walletServiceValidationException){

        final String exceptionMessage = MessageKey.messageExtractor(walletServiceValidationException.getLocalizedMessage());

        log.error("Validation exception occurred : " + exceptionMessage + " at time : " + LocalDateTime.now() + " Request : "
                  +" Exception: " + walletServiceValidationException);

        return new ResponseEntity<>(new WalletOperationServiceResponse<>(Constant.OPERATION_FAILED,exceptionMessage,Constant.FAILURE),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InternalSystemException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> unexpectedInternalTechnicalError(Exception exception){

        final String exceptionMessage = MessageKey.messageExtractor(MessageKey.UNEXPECTED_TECHNICAL_ERROR);

        log.error("Unexpected internal server error occurred : "+ Arrays.toString(exception.getStackTrace())+" at time : "+LocalDateTime.now());

        return new ResponseEntity<>(exceptionMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
