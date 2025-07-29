package com.arthur.ecommerceapi.dtos.request;

import jakarta.validation.constraints.NotNull;

public record CustomerPutRequestDTO (
        @NotNull
        Long id,

        String name,

        String email ,

        String phone
){}
