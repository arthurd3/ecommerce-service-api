package com.arthur.ecommerceapi.usecases;

import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateCustomer {

    private final CustomerGateway customerGateway;
    private final ValidatorCustomer validatorCustomer;

    @Transactional
    public Customer update(Customer updatedCustomer){
        validatorCustomer.validate(updatedCustomer);
        return customerGateway.save(updatedCustomer);
    }
}
