package com.arthur.ecommerceapi.customers.controllers.mappers;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.dtos.request.AddressPutRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.request.AddressRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.response.AddressResponseDTO;
import org.mapstruct.*;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = IGNORE)
public interface AddressMapper {

    Address toDomain(AddressRequestDTO addressRequestDTO);

    AddressResponseDTO toDTO(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id" , source = "addressId")
    @Mapping(target = "customer", ignore = true)
    Address updateFromDTO(AddressPutRequestDTO dto , final Long addressId);
}
