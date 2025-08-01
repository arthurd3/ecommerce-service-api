package com.arthur.ecommerceapi.orders.controllers.mapppers;

import com.arthur.ecommerceapi.orders.domain.model.Order;
import com.arthur.ecommerceapi.orders.dtos.response.OrderResponseDTO;

import org.mapstruct.Mapper;


import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING , unmappedTargetPolicy = IGNORE)
public interface OrderMapper {

    OrderResponseDTO toDto(Order order);
}
