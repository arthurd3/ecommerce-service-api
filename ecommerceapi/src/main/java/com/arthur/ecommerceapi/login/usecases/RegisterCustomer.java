package com.arthur.ecommerceapi.login.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.usecases.CreateCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterCustomer {

    private final CreateCustomer createCustomer;

    public void registerCustomer(final Customer customerRegister) {
        createCustomer.create(customerRegister);
    }

}
