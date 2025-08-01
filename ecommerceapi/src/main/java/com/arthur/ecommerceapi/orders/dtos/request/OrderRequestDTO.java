package com.arthur.ecommerceapi.orders.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderRequestDTO(
        String specification,
        @NotNull
        Long customerId ,
        @NotNull
        UUID productId ,
        @NotNull
        Long toAddressId
) {}
