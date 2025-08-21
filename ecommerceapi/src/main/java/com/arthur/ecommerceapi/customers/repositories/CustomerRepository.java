package com.arthur.ecommerceapi.customers.repositories;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Boolean existsByEmailIgnoreCase(final String email);

    Boolean existsByPhone(final String phone);

    Page<CustomerEntity> findAll(final Pageable pageable);

    Optional<CustomerEntity> findByEmail(final String email);

}
