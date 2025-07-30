package com.arthur.ecommerceapi.dtos.request;

import jakarta.validation.constraints.NotNull;

public record AddressPutRequestDTO (
        @NotNull
        Long addressId,

        String street,

        String city,

        String state,

        String zip,

        String country
) {}
