package com.example.testApplication.exception;

public class CardIsNotActiveException extends Exception{

    public CardIsNotActiveException() {
        super("Card is not active");
    }
}
