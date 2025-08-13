package com.arthur.ecommerceapi.customers.gateways;


import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;

public interface AddressGateway {
    Address save(final Address address);

    Address findById(final Long addressId);

    Address update(final Address address);

    AddressEntity findAddressEntity(final Long addressId);
}
