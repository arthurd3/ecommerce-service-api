package com.arthur.ecommerceapi.gateways.mappers;

import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.gateways.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = IGNORE)
public interface CustomerGatewayMapper {

    @Mapping(target = "address",ignore = true)
    Customer toDomain(CustomerEntity entity);

    CustomerEntity toEntity(Customer customer);
}
