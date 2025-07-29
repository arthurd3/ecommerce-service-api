package com.arthur.ecommerceapi.gateways.mappers;

import com.arthur.ecommerceapi.domain.model.Address;
import com.arthur.ecommerceapi.gateways.entities.AddressEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = IGNORE)
public interface AddressGatewayMapper {

    Address toDomain(AddressEntity entity);

    AddressEntity toEntity(Address address);

}
