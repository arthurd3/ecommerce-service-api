package com.arthur.ecommerceapi.usecases;

import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindCustomer {

    private final CustomerGateway customerGateway;

    public Customer find(final Long id){
        return customerGateway.findById(id);
    }

}
