package com.arthur.ecommerceapi.customers.gateways;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerGateway {
    Customer save(final Customer customer);

    Boolean existsByEmail(final String email);

    Boolean existsByPhone(final String phone);

    Page<Customer> findAll(final Pageable pageable);

    Customer findById(final Long id);

    void delete(final Long id);

    Boolean existsById(final Long id);

    Customer update(final Customer updatedCustomer);

    CustomerEntity findEntityById(final Long id);
}
