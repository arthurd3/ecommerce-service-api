package com.arthur.ecommerceapi.usecases;

import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.exceptions.UserAlreadyExistsException;
import com.arthur.ecommerceapi.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerValidator {

    private final CustomerGateway customerGateway;

    public void validate(final Customer customer) {
        if (customerGateway.existsByEmail(customer.getEmail()))
            throw new UserAlreadyExistsException("Email already exists");

        if(customerGateway.existsByPhone(customer.getPhone()))
            throw new UserAlreadyExistsException("Phone already exists");
    }

}
