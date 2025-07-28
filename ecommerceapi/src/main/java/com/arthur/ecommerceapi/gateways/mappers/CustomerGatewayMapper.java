package com.arthur.ecommerceapi.gateways.mappers;

import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.gateways.entities.CustomerEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = IGNORE)
public interface CustomerGatewayMapper {
    Customer toDomain(CustomerEntity entity);
    CustomerEntity toEntity(Customer customer);
}
