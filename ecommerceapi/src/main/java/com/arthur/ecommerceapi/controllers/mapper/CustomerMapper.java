package com.arthur.ecommerceapi.controllers.mapper;

import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.dtos.request.CustomerRequestDTO;
import com.arthur.ecommerceapi.dtos.response.CustomerResponseDTO;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = IGNORE)
public interface CustomerMapper {

    Customer toDomain(CustomerRequestDTO dto);

    CustomerResponseDTO toDTO(Customer customer);
}
