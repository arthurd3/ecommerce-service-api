package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.exceptions.BadLoginCredentialsException;
import com.arthur.ecommerceapi.customers.exceptions.EmailAlreadyExistsException;
import com.arthur.ecommerceapi.customers.exceptions.PhoneAlreadyExistsException;
import com.arthur.ecommerceapi.customers.exceptions.UserNotFoundException;
import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
import com.arthur.ecommerceapi.login.dtos.request.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidatorCustomer {

    private final CustomerGateway customerGateway;
    private final PasswordEncoder passwordEncoder;

    public void existsByEmailAndPhone(final String email, final String phone) {
        if (customerGateway.existsByEmail(email))
            throw new EmailAlreadyExistsException("Email already exists");

        if(customerGateway.existsByPhone(phone))
            throw new PhoneAlreadyExistsException("Phone already exists");
    }

    public void validateExists(final Long id){
        if(!customerGateway.existsById(id))
            throw new UserNotFoundException("Customer not exists");
    }

    public Customer validateLogin(final LoginRequestDTO login){
        var originalCustomer = customerGateway.findByEmail(login.email());

        if(!passwordEncoder.matches(login.password(), originalCustomer.getPassword())){
            throw new BadLoginCredentialsException("Invalid credentials");
        }

        return originalCustomer;
    }

}
