package com.arthur.ecommerceapi.customer.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDTO(
        @NotBlank
        String name,

        @NotBlank
        String email ,

        @NotBlank
        String phone ,

        @NotBlank
        String password
) {}
