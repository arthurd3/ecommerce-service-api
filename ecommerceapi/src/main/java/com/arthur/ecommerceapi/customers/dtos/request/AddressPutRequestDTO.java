package com.arthur.ecommerceapi.customers.dtos.request;

public record AddressPutRequestDTO (
        String street,

        String city,

        String state,

        String zip,

        String country
) {}
