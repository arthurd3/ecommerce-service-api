package com.arthur.ecommerceapi.login.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank
        String email,

        @NotBlank
        String password
) {}
