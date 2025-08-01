package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindCustomerEntity {

    private final CustomerGateway customerGateway;

    public CustomerEntity findById(final Long customerId) {
        return customerGateway.findEntityById(customerId);
    }

}
