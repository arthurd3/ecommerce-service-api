package com.arthur.ecommerceapi.customers.exceptions;

public class UserAlreadyHaveAddress extends RuntimeException {
    public UserAlreadyHaveAddress(String message) {
        super(message);
    }
}
