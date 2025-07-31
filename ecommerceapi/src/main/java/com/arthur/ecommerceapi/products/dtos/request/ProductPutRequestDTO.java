package com.arthur.ecommerceapi.products.dtos.request;

import com.arthur.ecommerceapi.products.domain.models.Coin;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;

public record ProductPutRequestDTO(
        String name,

        Coin price,

        String description,

        ProductCategory category,

        Integer quantity,

        Boolean availableToDiscount
) {}
