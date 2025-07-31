package com.arthur.ecommerceapi.customer.gateways.mappers;


import com.arthur.ecommerceapi.customer.domain.model.Address;
import com.arthur.ecommerceapi.customer.domain.model.Customer;
import com.arthur.ecommerceapi.customer.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customer.gateways.entities.CustomerEntity;
import org.mapstruct.*;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface GatewayMapper {

    @Mapping(target = "customer.address", ignore = true)
    Address addressToDomain(AddressEntity entity);
    @Mapping(target = "customer.address", ignore = true)
    AddressEntity addressToEntity(Address address);

    Customer customerToDomain(CustomerEntity entity);
    CustomerEntity customerToEntity(Customer customer);

    //CopyProperties
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void editEntityFromDomain(Address domainSource, @MappingTarget AddressEntity entityTarget);
}

