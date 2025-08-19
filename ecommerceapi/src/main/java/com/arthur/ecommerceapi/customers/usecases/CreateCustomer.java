package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomer {

    private final CustomerGateway customerGateway;
    private final PasswordEncoder passwordEncoder;
    private final ValidatorCustomer validatorCustomer;

    public Customer create(final Customer customer) {
        validatorCustomer.validate(customer);

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        return customerGateway.save(customer);
    }
}
