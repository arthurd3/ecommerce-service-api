package com.arthur.ecommerceapi.orders.controllers.mapppers;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.orders.dtos.response.CustomerOderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING , unmappedTargetPolicy = IGNORE)
public interface CustomerResponseMapper {

    @Mapping(target = "customerId" , source = "id")
    CustomerOderResponseDTO toDto(Customer customer);
}
