package com.arthur.ecommerceapi.controllers.mapper;

import com.arthur.ecommerceapi.domain.model.Address;
import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.dtos.request.AddressRequestDTO;
import com.arthur.ecommerceapi.dtos.response.AddressResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = IGNORE)
public interface AddressMapper {

    @Mapping(target = "id" , ignore = true)
    @Mapping(source = "customer", target = "customer")
    Address toDomain(AddressRequestDTO addressRequestDTO , Customer customer);

    AddressResponseDTO toDTO(Address address);
}
