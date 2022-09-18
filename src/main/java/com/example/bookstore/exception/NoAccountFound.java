package com.example.bookstore.exception;

public class NoAccountFound extends RuntimeException {
    public NoAccountFound(String message) {
        super(message);
    }
}
