package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
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

    public void validateLogin(final LoginRequestDTO login){
        if(customerGateway.existsByEmail(login.email()) || passwordEncoder.matches(login.password(), login.password()))
            throw new BadCredentialsException("Invalid Credentials try again");
    }

}
