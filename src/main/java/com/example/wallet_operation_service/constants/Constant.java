package com.example.wallet_operation_service.constants;

import com.example.wallet_operation_service.controller.WalletController;
import com.example.wallet_operation_service.service.WalletOperationService;

/**
 * Default Constant class for fulfill best practices in Java. We control all constants in this class.
 */
public class Constant {

    private Constant(){
        throw new IllegalStateException(CONSTANT_CONSTRUCTOR_ACCESS_ERROR);
    }

    private static final String CONSTANT_CONSTRUCTOR_ACCESS_ERROR = "Constant.java cannot be accessed with constructor";

    public static final String MESSAGE_RESOURCE_FILE_NAME = "WalletServiceMessage.properties";

    public static final int SUCCESS = 0;

    public static final int FAILURE = -1;

    public static final String CREDIT_OPERATION = "Credit Operation";

    public static final String WITHDRAWAL_OPERATION = "Withdrawal Operation";
}
