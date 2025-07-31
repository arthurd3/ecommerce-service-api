package com.arthur.ecommerceapi.products.gateways.mappers;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;
import org.mapstruct.*;


import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING , unmappedTargetPolicy = IGNORE)
public interface ProductGatewayMapper {

    @Mapping(target = "price", expression = "java(product.productPrice())")
    ProductEntity toEntity(Product product);

    @Mapping(target = "price", expression = "java(productEntity.productPrice())")
    Product toDomain(ProductEntity productEntity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void editProductEntityFromDomain(Product domainSource, @MappingTarget ProductEntity entityTarget);

}
