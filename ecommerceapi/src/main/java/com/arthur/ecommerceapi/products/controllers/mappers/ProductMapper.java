package com.arthur.ecommerceapi.products.controllers.mappers;

import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.dtos.request.ProductRequestDTO;
import com.arthur.ecommerceapi.products.dtos.response.ProductResponseDTO;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING , unmappedTargetPolicy = IGNORE)
public interface ProductMapper {

    Product toDomain(ProductRequestDTO dto);

    ProductResponseDTO toDTO(Product product);

}
