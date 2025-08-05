package com.arthur.ecommerceapi.shared.infrastructure.exception.handler;

import com.arthur.ecommerceapi.customers.exceptions.*;
import com.arthur.ecommerceapi.orders.exceptions.OrderNotFoundExecption;
import com.arthur.ecommerceapi.products.exceptions.ProductNotFoundException;
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

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ExceptionFilters illegalArgumentException(EmailAlreadyExistsException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(CONFLICT.value())
                .title("EmailAlreadyExistsException")
                .build();
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(PhoneAlreadyExistsException.class)
    public ExceptionFilters illegalArgumentException(PhoneAlreadyExistsException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(CONFLICT.value())
                .title("PhoneAlreadyExistsException")
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

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(AddressNotFoundException.class)
    public ExceptionFilters illegalArgumentException(AddressNotFoundException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(NOT_FOUND.value())
                .title("UserNotFoundException")
                .build();
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(UserAlreadyHaveAddressException.class)
    public ExceptionFilters illegalArgumentException(UserAlreadyHaveAddressException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(CONFLICT.value())
                .title("UserAlreadyHaveAddress")
                .build();
    }


    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ExceptionFilters illegalArgumentException(ProductNotFoundException ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(NOT_FOUND.value())
                .title("ProductNotFoundException")
                .build();
    }


    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(OrderNotFoundExecption.class)
    public ExceptionFilters illegalArgumentException(OrderNotFoundExecption ex) {
        return ExceptionFilters.builder()
                .timestamp(LocalDateTime.now())
                .details(ex.getMessage())
                .devMsg(ex.getClass().getName())
                .status(NOT_FOUND.value())
                .title("OrderNotFoundExecption")
                .build();
    }


}
