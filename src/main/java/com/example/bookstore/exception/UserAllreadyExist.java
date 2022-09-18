package com.example.bookstore.exception;

public class UserAllreadyExist extends RuntimeException{
    public UserAllreadyExist(String message) {
        super(message);
    }
}
