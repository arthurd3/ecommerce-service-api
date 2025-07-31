package com.arthur.ecommerceapi.customers.dtos.request;

import jakarta.validation.constraints.NotNull;

public record CustomerPutRequestDTO (
        @NotNull
        Long customerId,

        String name,

        String email ,

        String phone
){}
