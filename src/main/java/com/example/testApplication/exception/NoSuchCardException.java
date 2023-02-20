package com.example.testApplication.exception;

public class NoSuchCardException extends Exception {

    public NoSuchCardException() {
        super("No such card");
    }
}
