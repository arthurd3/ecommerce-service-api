package com.arthur.ecommerceapi.customer.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(@NotBlank
                              String email,

                              @NotBlank
                              String password
) {}
