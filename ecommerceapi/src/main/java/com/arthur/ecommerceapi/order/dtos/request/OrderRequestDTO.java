package com.arthur.ecommerceapi.order.dtos.request;

import jakarta.validation.constraints.NotNull;

public record OrderRequestDTO(
        String specification,
        @NotNull
        Long customerId ,
        @NotNull
        Long productId ,
        @NotNull
        Long toAddressId
) {}
