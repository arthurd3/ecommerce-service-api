package com.arthur.ecommerceapi.customers.gateways.mappers;


import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
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

    //CopyProperties Address
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void editAddressEntityFromDomain(Address domainSource, @MappingTarget AddressEntity entityTarget);

    //CopyProperties Customer
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void editCustomerEntityFromDomain(Customer domainSource, @MappingTarget CustomerEntity entityTarget);
}

