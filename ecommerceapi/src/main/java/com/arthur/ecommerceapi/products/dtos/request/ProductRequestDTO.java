package com.arthur.ecommerceapi.products.dtos.request;

import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDTO(
        @NotBlank
        String name,

        @NotNull
        Money price,

        @NotNull
        String description,

        @NotNull
        ProductCategory category,

        @NotNull
        Integer quantity,

        @NotNull
        Boolean availableToDiscount
) {}
