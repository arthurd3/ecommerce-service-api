package com.arthur.ecommerceapi.customer.controllers.mapper;

import com.arthur.ecommerceapi.customer.domain.model.Address;
import com.arthur.ecommerceapi.customer.dtos.request.AddressPutRequestDTO;
import com.arthur.ecommerceapi.customer.dtos.request.AddressRequestDTO;
import com.arthur.ecommerceapi.customer.dtos.response.AddressResponseDTO;
import org.mapstruct.*;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = IGNORE)
public interface AddressMapper {

    Address toDomain(AddressRequestDTO addressRequestDTO);

    AddressResponseDTO toDTO(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id" , source = "addressId")
    @Mapping(target = "customer" , ignore = true)
    Address updateFromDTO(AddressPutRequestDTO dto);
}
