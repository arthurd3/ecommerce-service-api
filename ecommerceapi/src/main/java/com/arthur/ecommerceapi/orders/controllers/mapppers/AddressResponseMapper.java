package com.arthur.ecommerceapi.orders.controllers.mapppers;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.orders.dtos.response.AddressOrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING , unmappedTargetPolicy = IGNORE)
public interface AddressResponseMapper {
    @Mapping(target = "addressId" , source = "id")
    AddressOrderResponseDTO toDto(Address address);
}
