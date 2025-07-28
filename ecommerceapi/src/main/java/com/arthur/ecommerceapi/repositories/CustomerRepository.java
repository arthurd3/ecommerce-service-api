package com.arthur.ecommerceapi.repositories;

import com.arthur.ecommerceapi.gateways.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
