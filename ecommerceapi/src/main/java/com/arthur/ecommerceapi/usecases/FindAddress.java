package com.arthur.ecommerceapi.usecases;

import com.arthur.ecommerceapi.domain.model.Address;
import com.arthur.ecommerceapi.gateways.AddressGateway;
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
