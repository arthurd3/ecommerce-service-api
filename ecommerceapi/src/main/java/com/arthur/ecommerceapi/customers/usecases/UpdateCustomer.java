package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCustomer {

    private final CustomerGateway customerGateway;
    private final ValidatorCustomer validatorCustomer;

    public Customer update(Customer updatedCustomer){
        validatorCustomer.validate(updatedCustomer);
        return customerGateway.save(updatedCustomer);
    }
}
