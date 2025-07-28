package com.arthur.ecommerceapi.gateways;

import com.arthur.ecommerceapi.domain.model.Customer;

public interface CustomerGateway {
    Customer save(Customer customer);
}
