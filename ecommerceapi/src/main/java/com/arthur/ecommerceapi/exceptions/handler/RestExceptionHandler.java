package com.arthur.ecommerceapi.exceptions.handler;

import com.arthur.ecommerceapi.exceptions.UserAlreadyExistsException;
import com.arthur.ecommerceapi.exceptions.UserAlreadyHaveAddress;
import com.arthur.ecommerceapi.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class RestExceptionHandler{

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ExceptionFilters illegalArgumentException(UserAlreadyExistsException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(CONFLICT.value())
                .title("UserAlreadyExistsException")
                .build();
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionFilters illegalArgumentException(UserNotFoundException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(NOT_FOUND.value())
                .title("UserNotFoundException")
                .build();
    }


    @ResponseStatus(CONFLICT)
    @ExceptionHandler(UserAlreadyHaveAddress.class)
    public ExceptionFilters illegalArgumentException(UserAlreadyHaveAddress ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(CONFLICT.value())
                .title("UserAlreadyHaveAddress")
                .build();
    }


}
