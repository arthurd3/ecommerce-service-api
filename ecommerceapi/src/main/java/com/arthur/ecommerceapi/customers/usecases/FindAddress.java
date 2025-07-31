package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.gateways.AddressGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAddress {

    private final AddressGateway addressGateway;

    public Address findById(final Long addressId) {
        return addressGateway.findById(addressId);
    }
}
