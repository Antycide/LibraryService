package com.example.libraryservice.exception;

public class BookAlreadyTakenException extends RuntimeException{
    public BookAlreadyTakenException(String message) {
        super(message);
    }
}
