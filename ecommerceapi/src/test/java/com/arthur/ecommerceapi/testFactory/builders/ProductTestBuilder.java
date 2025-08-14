package com.arthur.ecommerceapi.testFactory.builders;

import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
import com.arthur.ecommerceapi.products.dtos.request.ProductPutRequestDTO;
import com.arthur.ecommerceapi.products.dtos.request.ProductRequestDTO;
import com.arthur.ecommerceapi.products.dtos.response.ProductResponseDTO;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;

import java.util.UUID;

public class ProductTestBuilder {

    private UUID id;
    private String name;
    private Money price;
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

    public ProductTestBuilder withPrice(Money price) {
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

    public Product buildDomain() {
        return new Product(id , name , new Money(price.getValue().toString()) , description , category , quantity , availableToDiscount);
    }

    public ProductPutRequestDTO buildPutRequestDTO() {
        return new ProductPutRequestDTO(name , price.getValue() , description , category , quantity , availableToDiscount);
    }

    public ProductRequestDTO buildRequestDTO() {
        return new ProductRequestDTO(name , price.getValue(), description , category , quantity , availableToDiscount);
    }

    public ProductResponseDTO buildResponseDTO() {
        return new ProductResponseDTO(id , name , price.getFormatedValue() , description , category , quantity);
    }
}
