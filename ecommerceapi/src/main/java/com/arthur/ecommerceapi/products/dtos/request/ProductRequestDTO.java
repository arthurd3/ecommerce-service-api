package com.arthur.ecommerceapi.products.dtos.request;

import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDTO(
        @NotBlank
        String name,

        @NotNull
        BigDecimal price,

        @NotNull
        String description,

        @NotNull
        ProductCategory category,

        @NotNull
        Integer quantity,

        @NotNull
        Boolean availableToDiscount
) {}
