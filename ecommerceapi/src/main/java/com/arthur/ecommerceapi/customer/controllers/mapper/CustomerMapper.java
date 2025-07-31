package com.arthur.ecommerceapi.customer.controllers.mapper;

import com.arthur.ecommerceapi.customer.domain.model.Customer;
import com.arthur.ecommerceapi.customer.dtos.request.CustomerPutRequestDTO;
import com.arthur.ecommerceapi.customer.dtos.request.CustomerRequestDTO;
import com.arthur.ecommerceapi.customer.dtos.response.CustomerResponseDTO;
import org.mapstruct.*;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = IGNORE)
public interface CustomerMapper {

    Customer toDomain(CustomerRequestDTO dto);

    CustomerResponseDTO toDTO(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer updateFromDTO(CustomerPutRequestDTO dto, @MappingTarget Customer customer);
}
