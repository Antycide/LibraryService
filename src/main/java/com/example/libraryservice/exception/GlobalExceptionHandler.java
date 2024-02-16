package com.example.libraryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookDoesntExistException.class)
    public ResponseEntity<ErrorObject> handleBookDoesntExistException(BookDoesntExistException ex,
                                                                   WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setDate(new Date());
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookAlreadyTakenException.class)
    public ResponseEntity<ErrorObject> bookAlreadyTakenException(BookAlreadyTakenException ex,
                                                                 WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setDate(new Date());
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<ErrorObject>  bookAlreadyExistsException(BookAlreadyTakenException ex,
                                                                                  WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.ALREADY_REPORTED.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setDate(new Date());
        return new ResponseEntity<>(errorObject, HttpStatus.ALREADY_REPORTED);
    }
}
