package com.arthur.ecommerceapi.usecases;

import com.arthur.ecommerceapi.domain.model.Address;
import com.arthur.ecommerceapi.gateways.AddressGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAddress {

    private final AddressGateway addressGateway;

    public Address create(final Address address) {
        return addressGateway.save(address);
    }
}
