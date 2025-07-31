package com.arthur.ecommerceapi.customer.exceptions;

public class UserAlreadyHaveAddress extends RuntimeException {
    public UserAlreadyHaveAddress(String message) {
        super(message);
    }
}
