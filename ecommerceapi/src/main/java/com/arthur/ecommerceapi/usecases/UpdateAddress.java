package com.arthur.ecommerceapi.usecases;

import com.arthur.ecommerceapi.domain.model.Address;
import com.arthur.ecommerceapi.gateways.AddressGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateAddress {

    private final AddressGateway addressGateway;

    @Transactional
    public Address update(Address address) {
        return addressGateway.save(address);
    }
}
