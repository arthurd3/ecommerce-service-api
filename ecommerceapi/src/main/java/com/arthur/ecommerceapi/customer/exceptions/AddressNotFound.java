package com.arthur.ecommerceapi.customer.exceptions;

public class AddressNotFound extends RuntimeException {
    public AddressNotFound(String message) {
        super(message);
    }
}
