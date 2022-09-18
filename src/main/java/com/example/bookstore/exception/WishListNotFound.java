package com.example.bookstore.exception;

public class WishListNotFound extends RuntimeException{
    public WishListNotFound(String message) {
        super(message);
    }
}
