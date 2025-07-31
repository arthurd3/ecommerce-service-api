package com.arthur.ecommerceapi.product.dto.response;

import com.arthur.ecommerceapi.product.domain.model.enums.ProductCategory;

import java.util.UUID;

public record ProductResponseDTO (
        UUID id,

        String name,

        String formatedPrice,

        String description,

        ProductCategory category,

        Integer quantity
) {}
