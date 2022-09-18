package com.example.bookstore.exception;

public class BookNotFound extends RuntimeException{
    public BookNotFound(String message) {
        super(message);
    }
}
