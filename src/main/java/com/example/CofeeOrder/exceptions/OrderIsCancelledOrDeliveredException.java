package com.example.CofeeOrder.exceptions;

public class OrderIsCancelledOrDeliveredException extends RuntimeException {

    public OrderIsCancelledOrDeliveredException(String message) {
        super(message);
    }
}
