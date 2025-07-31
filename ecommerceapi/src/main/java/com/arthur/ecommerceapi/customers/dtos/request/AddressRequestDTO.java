package com.arthur.ecommerceapi.customers.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRequestDTO (
        @NotNull
        Long customerId,
        @NotBlank
        String street,
        @NotBlank
        String city,
        @NotBlank
        String state,
        @NotBlank
        String zip,
        @NotBlank
        String country
){}
