package com.arthur.ecommerceapi.products.dtos.request;

import com.arthur.ecommerceapi.products.domain.models.Coin;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProductPutRequestDTO(
        String name,

        Coin price,

        String description,

        ProductCategory category,

        Integer quantity,

        Boolean availableToDiscount
) {}
