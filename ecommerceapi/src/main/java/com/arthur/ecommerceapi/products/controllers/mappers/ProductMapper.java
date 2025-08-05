package com.arthur.ecommerceapi.products.controllers.mappers;

import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.dtos.request.ProductPutRequestDTO;
import com.arthur.ecommerceapi.products.dtos.request.ProductRequestDTO;
import com.arthur.ecommerceapi.products.dtos.response.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING , unmappedTargetPolicy = IGNORE)
public interface ProductMapper {

    Product toDomain(ProductRequestDTO dto);

    @Mapping(target = "formatedPrice" , expression = "java(product.productFormatedPrice())")
    ProductResponseDTO toDTO(Product product);

    @Mapping(target = "id" , source = "productId")
    Product updateFromDTO(ProductPutRequestDTO dto , UUID productId);
}
