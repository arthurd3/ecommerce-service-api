package com.arthur.ecommerceapi.customers.exceptions;

public class BadLoginCredentialsException extends RuntimeException {
    public BadLoginCredentialsException(String message) {
        super(message);
    }
}
