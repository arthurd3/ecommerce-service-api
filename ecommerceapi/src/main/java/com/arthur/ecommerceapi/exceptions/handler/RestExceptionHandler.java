package com.arthur.ecommerceapi.exceptions.handler;

import com.arthur.ecommerceapi.exceptions.UserAlreadyExistsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.CONFLICT;

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
}
