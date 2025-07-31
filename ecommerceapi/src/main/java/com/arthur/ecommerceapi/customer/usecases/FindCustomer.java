package com.arthur.ecommerceapi.customer.usecases;

import com.arthur.ecommerceapi.customer.domain.model.Customer;
import com.arthur.ecommerceapi.customer.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindCustomer {

    private final CustomerGateway customerGateway;

    public Customer findById(final Long id){
        return customerGateway.findById(id);
    }

}
