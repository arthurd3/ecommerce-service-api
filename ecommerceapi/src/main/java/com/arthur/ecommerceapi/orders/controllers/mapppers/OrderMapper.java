package com.arthur.ecommerceapi.orders.controllers.mapppers;

import com.arthur.ecommerceapi.orders.domain.model.Order;
import com.arthur.ecommerceapi.orders.dtos.response.OrderResponseDTO;

import com.arthur.ecommerceapi.products.controllers.mappers.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING,
        uses = {AddressResponseMapper.class, CustomerResponseMapper.class, ProductMapper.class} , unmappedTargetPolicy = IGNORE)
public interface OrderMapper {

    @Mapping(source = "orderId", target = "oderId")
    OrderResponseDTO toDto(Order order);

}
