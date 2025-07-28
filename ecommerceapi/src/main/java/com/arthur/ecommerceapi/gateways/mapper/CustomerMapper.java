package com.arthur.ecommerceapi.gateways.mapper;

import com.arthur.ecommerceapi.controllers.CustomerController;
import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.dtos.request.CustomerRequest;
import com.arthur.ecommerceapi.dtos.response.CustomerResponse;
import com.arthur.ecommerceapi.gateways.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING , unmappedTargetPolicy = IGNORE)
public interface CustomerMapper {

    Customer toDomain(CustomerRequest dto);

    CustomerEntity toEntity(Customer customer);

    CustomerResponse toResponse(Customer customer);
}
