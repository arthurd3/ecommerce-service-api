package com.arthur.ecommerceapi.usecases;

import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomer {

    private final CustomerGateway customerGateway;
    private final ValidatorCustomer validatorCustomer;

    public Customer create(final Customer customer) {
        validatorCustomer.validate(customer);
        return customerGateway.save(customer);
    }
}
