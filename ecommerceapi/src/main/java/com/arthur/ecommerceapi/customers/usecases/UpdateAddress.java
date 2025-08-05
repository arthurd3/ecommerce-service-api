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
    private final AddressMapper addressMapper;


    @Transactional
    public Address update(final Long addressId ,final AddressPutRequestDTO addressUpdate) {

        Address existingAddress = addressGateway.findById(addressId);

        addressMapper.updateFromDTO(addressUpdate, existingAddress);

        return addressGateway.update(existingAddress);
    }
}
