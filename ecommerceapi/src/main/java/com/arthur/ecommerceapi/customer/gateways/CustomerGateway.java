package com.arthur.ecommerceapi.customer.gateways;

import com.arthur.ecommerceapi.customer.domain.model.Customer;
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


}
