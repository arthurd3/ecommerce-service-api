package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.controllers.mappers.AddressMapper;
import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.dtos.request.AddressPutRequestDTO;
import com.arthur.ecommerceapi.customers.gateways.AddressGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateAddress {

    private final AddressGateway addressGateway;

    @Transactional
    public Address update(final Address address) {
        return addressGateway.update(address);
    }
}
