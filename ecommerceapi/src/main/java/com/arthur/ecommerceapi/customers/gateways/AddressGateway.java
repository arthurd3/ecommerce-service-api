package com.arthur.ecommerceapi.customers.gateways;


import com.arthur.ecommerceapi.customers.domain.model.Address;

public interface AddressGateway {
    Address save(final Address address);

    Boolean existsByCustomerId(final Long customerId);

    Address findById(final Long addressId);

    Address update(final Address address);
}
