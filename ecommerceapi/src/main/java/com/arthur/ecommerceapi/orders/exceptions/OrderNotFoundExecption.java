package com.arthur.ecommerceapi.orders.exceptions;

public class OrderNotFoundExecption extends RuntimeException {
    public OrderNotFoundExecption(String message) {
        super(message);
    }
}
