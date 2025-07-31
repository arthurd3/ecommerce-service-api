package com.arthur.ecommerceapi.customers.controllers.mappers;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.dtos.request.CustomerPutRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.request.CustomerRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.response.CustomerResponseDTO;
import org.mapstruct.*;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = IGNORE)
public interface CustomerMapper {

    Customer toDomain(CustomerRequestDTO dto);

    CustomerResponseDTO toDTO(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id" , source = "customerId")
    Customer updateFromDTO(CustomerPutRequestDTO dto);
}
