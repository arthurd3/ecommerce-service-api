package com.arthur.ecommerceapi.gateways;

import com.arthur.ecommerceapi.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerGateway {
    Customer save(Customer customer);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Page<Customer> findAll(Pageable pageable);

    Customer findById(Long id);

}
