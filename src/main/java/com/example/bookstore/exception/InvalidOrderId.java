package com.example.bookstore.exception;

public class InvalidOrderId extends RuntimeException {
    public InvalidOrderId(String message) {
        super(message);
    }
}
