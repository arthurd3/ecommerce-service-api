package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.domain.model.RoleValues;
import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
import com.arthur.ecommerceapi.roles.usecases.FindRole;
import com.arthur.ecommerceapi.shared.roles.usecases.FindRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomer {

    private final CustomerGateway customerGateway;
    private final PasswordEncoder passwordEncoder;
    private final ValidatorCustomer validatorCustomer;
    private final FindRole findRole;

    public Customer create(final Customer customer) {
        validatorCustomer.existsByEmailAndPhone(customer.getEmail(), customer.getPhone());

        var customerRole = findRole.findRole(RoleValues.CUSTOMER);
        
        customer.addRole(customerRole);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        return customerGateway.save(customer);
    }
}
