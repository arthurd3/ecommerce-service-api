package com.arthur.ecommerceapi.customer.gateways;


import com.arthur.ecommerceapi.customer.domain.model.Address;

public interface AddressGateway {
    Address save(final Address address);

    Boolean existsByCustomerId(final Long customerId);

    Address findById(final Long addressId);

    Address update(final Address address);
}
