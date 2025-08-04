package com.arthur.ecommerceapi.customers.exceptions;

public class UserAlreadyHaveAddressException extends RuntimeException {
    public UserAlreadyHaveAddressException(String message) {
        super(message);
    }
}
