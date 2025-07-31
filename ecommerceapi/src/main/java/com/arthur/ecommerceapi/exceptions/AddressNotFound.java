package com.arthur.ecommerceapi.exceptions;

public class AddressNotFound extends RuntimeException {
    public AddressNotFound(String message) {
        super(message);
    }
}
