package com.arthur.ecommerceapi.gateways.mapper;

import com.arthur.ecommerceapi.controllers.CustomerController;
import com.arthur.ecommerceapi.dtos.request.CustomerRequest;
import com.arthur.ecommerceapi.dtos.response.CustomerResponse;
import com.arthur.ecommerceapi.gateways.entities.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerController toDomain(CustomerRequest dto);

    CustomerEntity toEntity(CustomerController customerController);

    CustomerResponse toResponse(CustomerController customerController);
}
