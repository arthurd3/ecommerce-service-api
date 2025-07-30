package com.arthur.ecommerceapi.exceptions;

public class UserAlreadyHaveAddress extends RuntimeException {
    public UserAlreadyHaveAddress(String message) {
        super(message);
    }
}
