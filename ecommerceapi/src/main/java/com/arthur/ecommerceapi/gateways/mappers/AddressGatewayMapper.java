package com.arthur.ecommerceapi.gateways.mappers;

import com.arthur.ecommerceapi.domain.model.Address;
import com.arthur.ecommerceapi.gateways.entities.AddressEntity;
import org.mapstruct.*;

import static org.mapstruct.ReportingPolicy.IGNORE;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = IGNORE,
        uses = {CustomerGatewayMapper.class})
public interface AddressGatewayMapper {

    Address toDomain(AddressEntity entity);

    AddressEntity toEntity(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    void updateEntityFromDomain(Address domainSource, @MappingTarget AddressEntity entityTarget);

}
