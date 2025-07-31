package com.arthur.ecommerceapi.usecases;

import com.arthur.ecommerceapi.domain.model.Address;
import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.exceptions.UserAlreadyHaveAddress;
import com.arthur.ecommerceapi.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateAddress {

    private final FindCustomer findCustomer;
    private final CustomerGateway customerGateway;

    @Transactional
    public Address create(final Address address , final Long customerId) {

        Customer customer = findCustomer.findById(customerId);

        if (customer.getAddress() != null) {
            throw new UserAlreadyHaveAddress("This User already has an address");
        }

        customer.defineAddress(address);
        address.defineCustomer(customer);

        Customer savedCustomer = customerGateway.save(customer);

        return savedCustomer.getAddress();
    }
}
