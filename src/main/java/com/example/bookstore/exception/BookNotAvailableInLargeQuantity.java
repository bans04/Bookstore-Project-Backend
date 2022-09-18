package com.example.bookstore.exception;

public class BookNotAvailableInLargeQuantity extends RuntimeException {
    public BookNotAvailableInLargeQuantity(String message) {
        super(message);
    }
}
