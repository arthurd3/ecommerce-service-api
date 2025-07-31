package com.arthur.ecommerceapi.products.domain.models;

import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Product {
    private String name;
    private Coin price;
    private String description;
    private ProductCategory category;
    private Integer quantity;
    private Boolean availableToDiscount;

    public BigDecimal productPrice() {
        return price.getValue();
    }
}
