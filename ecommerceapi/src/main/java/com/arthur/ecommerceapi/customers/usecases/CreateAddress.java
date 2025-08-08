package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.exceptions.UserAlreadyHaveAddressException;
import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
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
            throw new UserAlreadyHaveAddressException("This User already has an address");
        }

        customer.defineAddress(address);
        address.defineCustomer(customer);

        Customer savedCustomer = customerGateway.save(customer);

        return savedCustomer.getAddress();
    }
}
