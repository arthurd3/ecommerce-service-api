package com.arthur.ecommerceapi.gateways.mapper;

import com.arthur.ecommerceapi.controllers.Customer;
import com.arthur.ecommerceapi.gateways.entities.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toDomain();

    CustomerEntity toEntity();


}
