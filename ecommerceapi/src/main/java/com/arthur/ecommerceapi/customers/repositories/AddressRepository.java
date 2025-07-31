package com.arthur.ecommerceapi.customers.repositories;

import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    Boolean existsByCustomer_Id(Long customerId);
}
