package com.example.libraryservice.exception;

public class BookDoesntExistException extends RuntimeException{

    public BookDoesntExistException(String message) {
        super(message);
    }

}
