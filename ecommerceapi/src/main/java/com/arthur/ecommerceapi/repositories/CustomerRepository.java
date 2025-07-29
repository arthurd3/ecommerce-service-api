package com.arthur.ecommerceapi.repositories;

import com.arthur.ecommerceapi.gateways.entities.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Boolean existsByEmailIgnoreCase(String email);

    Boolean existsByPhone(String phone);

    Page<CustomerEntity> findAll(Pageable pageable);

}
