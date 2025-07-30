package com.arthur.ecommerceapi.gateways;

import com.arthur.ecommerceapi.domain.model.Address;

public interface AddressGateway {
    Address save(final Address address);

    Boolean existsByCustomerId(final Long customerId);

    Address findById(final Long addressId);

    Address update(final Address address);
}
