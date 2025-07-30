package com.arthur.ecommerceapi.usecases;

import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.exceptions.UserAlreadyExistsException;
import com.arthur.ecommerceapi.exceptions.UserNotFoundException;
import com.arthur.ecommerceapi.gateways.CustomerGateway;
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
