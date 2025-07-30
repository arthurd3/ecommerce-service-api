package com.arthur.ecommerceapi.usecases;

import com.arthur.ecommerceapi.domain.model.Address;
import com.arthur.ecommerceapi.exceptions.UserAlreadyHaveAddress;
import com.arthur.ecommerceapi.gateways.AddressGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateAddress {

    private final AddressGateway addressGateway;
    private final FindCustomer findCustomer;

    @Transactional
    public Address create(final Address address , final Long customerId) {
        if(addressGateway.existsByCustomerId(customerId))
            throw new UserAlreadyHaveAddress("This User have already a address");

        address.defineCustomer(findCustomer.findById(customerId));
        return addressGateway.save(address);
    }
}
