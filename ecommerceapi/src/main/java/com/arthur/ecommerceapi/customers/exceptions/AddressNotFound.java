package com.arthur.ecommerceapi.customers.exceptions;

public class AddressNotFound extends RuntimeException {
    public AddressNotFound(String message) {
        super(message);
    }
}
