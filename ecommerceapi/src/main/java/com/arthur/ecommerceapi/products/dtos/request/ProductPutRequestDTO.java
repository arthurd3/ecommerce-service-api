package com.arthur.ecommerceapi.products.dtos.request;

import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;

public record ProductPutRequestDTO(
        String name,

        Money price,

        String description,

        ProductCategory category,

        Integer quantity,

        Boolean availableToDiscount
) {}
