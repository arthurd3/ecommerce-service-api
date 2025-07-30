package com.arthur.ecommerceapi.controllers.mapper;

import com.arthur.ecommerceapi.domain.model.Address;
import com.arthur.ecommerceapi.dtos.request.AddressRequestDTO;
import com.arthur.ecommerceapi.dtos.response.AddressResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = IGNORE)
public interface AddressMapper {

    @Mapping(target = "id",ignore = true)
    Address toDomain(AddressRequestDTO addressRequestDTO);

    AddressResponseDTO toDTO(Address address);
}
