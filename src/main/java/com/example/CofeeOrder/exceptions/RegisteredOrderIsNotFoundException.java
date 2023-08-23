package com.example.CofeeOrder.exceptions;

public class RegisteredOrderIsNotFoundException extends RuntimeException {

    public RegisteredOrderIsNotFoundException(String message) {
        super(message);
    }
}
