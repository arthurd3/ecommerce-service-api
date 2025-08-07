package com.arthur.ecommerceapi.customers.gateways;


import com.arthur.ecommerceapi.customers.domain.model.Address;

public interface AddressGateway {
    Address save(final Address address);

    Address findById(final Long addressId);

    Address update(final Address address);
}
