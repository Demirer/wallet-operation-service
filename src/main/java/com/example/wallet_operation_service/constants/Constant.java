package com.example.wallet_operation_service.constants;

/**
 * Default Constant class for fulfill best practices in Java. We control all constants in this class.
 */
public class Constant {

    private Constant(){
        throw new IllegalStateException(CONSTANT_CONSTRUCTOR_ACCESS_ERROR);
    }

    private static final String CONSTANT_CONSTRUCTOR_ACCESS_ERROR = "Constant.java cannot be accessed with constructor";

    public static final int SUCCESS = 0;
}
