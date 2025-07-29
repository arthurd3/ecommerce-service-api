package com.arthur.ecommerceapi.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record CustomerPutAddressDTO (
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
