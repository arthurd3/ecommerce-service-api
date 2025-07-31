package com.arthur.ecommerceapi.product.dto.request;

import com.arthur.ecommerceapi.product.domain.model.Coin;
import com.arthur.ecommerceapi.product.domain.model.enums.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDTO(
        @NotBlank
        String name,

        @NotNull
        Coin price,

        @NotNull
        String description,

        @NotNull
        ProductCategory category,

        @NotNull
        Integer quantity,

        @NotNull
        Boolean availableToDiscount
) {}
