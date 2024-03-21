package com.example.libraryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookDoesntExistException.class)
    public ErrorObject handleBookDoesntExistException(BookDoesntExistException ex,
                                                                   WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setDate(new Date());
        return errorObject;
    }

    @ExceptionHandler(BookAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorObject bookAlreadyTakenException(BookAlreadyTakenException ex,
                                                                 WebRequest request) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setDate(new Date());
        return errorObject;
    }

}
