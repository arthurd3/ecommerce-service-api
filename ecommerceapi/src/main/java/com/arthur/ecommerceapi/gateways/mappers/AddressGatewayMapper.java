package com.arthur.ecommerceapi.gateways.mappers;

import com.arthur.ecommerceapi.domain.model.Address;
import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.gateways.entities.CustomerEntity;
import org.mapstruct.*;

import static org.mapstruct.ReportingPolicy.IGNORE;


@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface AddressGatewayMapper {

    Address toDomain(AddressEntity entity);

    @Mapping(target = "address", ignore = true)
    Customer customerEntityToCustomer(CustomerEntity customerEntity);

    AddressEntity toEntity(Address address);

    @Mapping(target = "address", ignore = true)
    CustomerEntity customerToCustomerEntity(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    void updateEntityFromDomain(Address domainSource, @MappingTarget AddressEntity entityTarget);

}
