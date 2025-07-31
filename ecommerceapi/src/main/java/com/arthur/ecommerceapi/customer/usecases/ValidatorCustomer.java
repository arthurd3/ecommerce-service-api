package com.arthur.ecommerceapi.customer.usecases;

import com.arthur.ecommerceapi.customer.domain.model.Customer;
import com.arthur.ecommerceapi.customer.exceptions.UserAlreadyExistsException;
import com.arthur.ecommerceapi.customer.exceptions.UserNotFoundException;
import com.arthur.ecommerceapi.customer.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidatorCustomer {

    private final CustomerGateway customerGateway;

    public void validate(final Customer customer) {
        if (customerGateway.existsByEmail(customer.getEmail()))
            throw new UserAlreadyExistsException("Email already exists");

        if(customerGateway.existsByPhone(customer.getPhone()))
            throw new UserAlreadyExistsException("Phone already exists");
    }

    public void validateExists(final Long id){
        if(!customerGateway.existsById(id))
            throw new UserNotFoundException("Customer not exists");
    }

}
