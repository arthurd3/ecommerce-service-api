package com.arthur.ecommerceapi.customer.usecases;

import com.arthur.ecommerceapi.customer.domain.model.Address;
import com.arthur.ecommerceapi.customer.gateways.AddressGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateAddress {

    private final AddressGateway addressGateway;

    @Transactional
    public Address update(final Address addressUpdate) {
        return addressGateway.update(addressUpdate);
    }
}
