package com.example.wallet_operation_service.constants;

public class Constant {

    private Constant(){
        throw new IllegalStateException(CONSTANT_CONSTRUCTOR_ACCESS_ERROR);
    }

    private static final String CONSTANT_CONSTRUCTOR_ACCESS_ERROR = "Constant.java cannot be accessed with constructor";

    public static final String MESSAGE_RESOURCE_FILE_NAME = "WalletServiceMessage";

    public static final int SUCCESS = 0;

    public static final int FAILURE = -1;

    public static final String CREDIT_OPERATION = "Credit Operation";

    public static final String WITHDRAWAL_OPERATION = "Withdrawal Operation";

    public static final String OPERATION_FAILED = "Operation failed.";
}