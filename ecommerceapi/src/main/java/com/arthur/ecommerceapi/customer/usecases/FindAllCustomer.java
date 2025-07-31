package com.arthur.ecommerceapi.customer.usecases;

import com.arthur.ecommerceapi.customer.domain.model.Customer;
import com.arthur.ecommerceapi.customer.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAllCustomer {

    private final CustomerGateway customerGateway;

    public Page<Customer> findAll(Pageable pageable) {
        return customerGateway.findAll(pageable);
    }
}
