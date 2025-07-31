package com.arthur.ecommerceapi.products.dtos.response;

import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;

import java.util.UUID;

public record ProductResponseDTO (
        UUID id,

        String name,

        String formatedPrice,

        String description,

        ProductCategory category,

        Integer quantity
) {}
