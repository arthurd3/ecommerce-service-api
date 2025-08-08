package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAllCustomers {

    private final CustomerGateway customerGateway;

    public Page<Customer> findAll(Pageable pageable) {
        return customerGateway.findAll(pageable);
    }
}
