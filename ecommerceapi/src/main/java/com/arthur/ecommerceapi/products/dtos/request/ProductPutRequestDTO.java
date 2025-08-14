package com.arthur.ecommerceapi.products.dtos.request;

import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;

import java.math.BigDecimal;

public record ProductPutRequestDTO(
        String name,

        BigDecimal price,

        String description,

        ProductCategory category,

        Integer quantity,

        Boolean availableToDiscount
) {}
