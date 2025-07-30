package com.arthur.ecommerceapi.controllers.mapper;

import com.arthur.ecommerceapi.domain.model.Address;
import com.arthur.ecommerceapi.dtos.request.AddressPutRequestDTO;
import com.arthur.ecommerceapi.dtos.request.AddressRequestDTO;
import com.arthur.ecommerceapi.dtos.response.AddressResponseDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = IGNORE)
public interface AddressMapper {

    Address toDomain(AddressRequestDTO addressRequestDTO);

    AddressResponseDTO toDTO(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address updateFromDTO(AddressPutRequestDTO dto, @MappingTarget Address address);
}
