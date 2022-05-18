package com.example.wallet_operation_service.exception_handler;

import com.example.wallet_operation_service.constants.Constant;

import java.util.ResourceBundle;

public class MessageKey {

    private MessageKey(){

        throw new IllegalStateException(EXCEPTION_MESSAGE_KEY_ACCESS_ERROR);
    }

    private static final String EXCEPTION_MESSAGE_KEY_ACCESS_ERROR = "MessageKey.java cannot be accessed with constructor";

    public static final String UNEXPECTED_TECHNICAL_ERROR = "exception.unexpected_technical_exception";

    public static final String CUSTOMER_NOT_EXIST = "validation.customer_not_exist";

    public static final String TRANSACTION_ID_UNIQUENESS = "validation.transaction_id_uniqueness";

    public static final String INSUFFICIENT_FUNDS = "validation.insufficient_funds";

    public static final String ARGUMENTS_NOT_FOUND = "validation.arguments_not_found";

    public static final String WALLET_DETAILS_SUCCESS_MESSAGE = "success.wallet_details_success_message";

    public static final String WITHDRAWAL_SUCCESS_MESSAGE = "success.withdrawal_operation_success";

    public static final String CREDIT_SUCCESS_MESSAGE = "success.credit_operation_success";

    public static String messageExtractor(String key) {
        return ResourceBundle.getBundle(Constant.MESSAGE_RESOURCE_FILE_NAME).getString(key);
    }
}
