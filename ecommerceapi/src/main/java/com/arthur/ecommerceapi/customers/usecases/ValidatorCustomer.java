package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.exceptions.EmailAlreadyExistsException;
import com.arthur.ecommerceapi.customers.exceptions.PhoneAlreadyExistsException;
import com.arthur.ecommerceapi.customers.exceptions.UserAlreadyExistsException;
import com.arthur.ecommerceapi.customers.exceptions.UserNotFoundException;
import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidatorCustomer {

    private final CustomerGateway customerGateway;

    public void validate(final Customer customer) {
        if (customerGateway.existsByEmail(customer.getEmail()))
            throw new EmailAlreadyExistsException("Email already exists");

        if(customerGateway.existsByPhone(customer.getPhone()))
            throw new PhoneAlreadyExistsException("Phone already exists");
    }

    public void validateExists(final Long id){
        if(!customerGateway.existsById(id))
            throw new UserNotFoundException("Customer not exists");
    }

}
