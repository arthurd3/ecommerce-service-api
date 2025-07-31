package com.arthur.ecommerceapi.customer.usecases;

import com.arthur.ecommerceapi.customer.domain.model.Address;
import com.arthur.ecommerceapi.customer.gateways.AddressGateway;
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
