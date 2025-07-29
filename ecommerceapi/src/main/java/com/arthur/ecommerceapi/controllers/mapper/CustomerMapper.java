package com.arthur.ecommerceapi.controllers.mapper;

import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.dtos.request.CustomerPutRequestDTO;
import com.arthur.ecommerceapi.dtos.request.CustomerRequestDTO;
import com.arthur.ecommerceapi.dtos.response.CustomerResponseDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = IGNORE)
public interface CustomerMapper {

    Customer toDomain(CustomerRequestDTO dto);

    CustomerResponseDTO toDTO(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer updateCustomerFromDTO(CustomerPutRequestDTO dto, @MappingTarget Customer customer);
}
