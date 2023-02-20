package com.example.testApplication.exception;

public class SubZeroBalanceException extends Exception{

    public SubZeroBalanceException() {
        super("Sub zero balance");
    }
}
