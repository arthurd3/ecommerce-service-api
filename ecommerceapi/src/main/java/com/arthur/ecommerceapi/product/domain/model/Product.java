package com.arthur.ecommerceapi.product.domain.model;

import com.arthur.ecommerceapi.product.domain.model.enums.ProductCategory;

public class Product {
    private String name;
    private Coin price;
    private String description;
    private ProductCategory category;
    private Integer quantity;
    private Boolean availableToDiscount;
}
