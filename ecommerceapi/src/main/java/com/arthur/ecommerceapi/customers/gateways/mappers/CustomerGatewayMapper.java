package com.arthur.ecommerceapi.customers.gateways.mappers;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.domain.model.Permission;
import com.arthur.ecommerceapi.customers.domain.model.Role;
import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.PermissionEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.RoleEntity;
import org.mapstruct.*;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE)
public interface CustomerGatewayMapper {

    //CUSTOMER
    Customer customerToDomain(CustomerEntity entity);

    CustomerEntity customerToEntity(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void editCustomerEntityFromDomain(Customer domainSource, @MappingTarget CustomerEntity entityTarget);

    //ADDRESS
    @Mapping(target = "customer.address", ignore = true)
    Address addressToDomain(AddressEntity entity);

    @Mapping(target = "customer.address", ignore = true)
    AddressEntity addressToEntity(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void editAddressEntityFromDomain(Address domainSource, @MappingTarget AddressEntity entityTarget);


    //PERMISSION
    Permission toDomain(PermissionEntity entity);

    PermissionEntity toEntity(Permission domain);

    //ROLE
    Role toDomain(RoleEntity entity);

    RoleEntity toEntity(Role domain);
}
