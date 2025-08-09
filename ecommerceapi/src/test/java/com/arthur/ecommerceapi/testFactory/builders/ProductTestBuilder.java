package com.arthur.ecommerceapi.testFactory.builders;

import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.products.domain.models.Coin;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;

import java.util.UUID;

public class ProductTestBuilder {

    private UUID id;
    private String name;
    private Coin price;
    private String description;
    private ProductCategory category;
    private Integer quantity;
    private Boolean availableToDiscount;

    public static ProductTestBuilder aProduct(){
        return new ProductTestBuilder();
    }

    public ProductTestBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public ProductTestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductTestBuilder withPrice(Coin price) {
        this.price = price;
        return this;
    }

    public ProductTestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductTestBuilder withCategory(ProductCategory category) {
        this.category = category;
        return this;
    }

    public ProductTestBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductTestBuilder withAvailableToDiscount(Boolean availableToDiscount) {
        this.availableToDiscount = availableToDiscount;
        return this;
    }


    public ProductEntity buildEntity() {
        return new ProductEntity(id , name , price.getValue() , description , category , quantity , availableToDiscount);
    }
    
}
