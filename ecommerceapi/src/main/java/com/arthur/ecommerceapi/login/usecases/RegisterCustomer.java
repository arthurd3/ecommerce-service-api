package com.arthur.ecommerceapi.login.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.usecases.CreateCustomer;
import com.arthur.ecommerceapi.customers.usecases.ValidatorCustomer;
import com.arthur.ecommerceapi.login.dtos.request.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterCustomer {

    private final CreateCustomer createCustomer;
    private final ValidatorCustomer validatorCustomer;

    public void registerCustomer(final Customer registerRequestDTO){
        validatorCustomer.existsByEmailAndPhone(registerRequestDTO.email(), registerRequestDTO.phone());
        createCustomer.create()
    }

}
