package com.arthur.ecommerceapi.customer.repositories;

import com.arthur.ecommerceapi.customer.gateways.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    Boolean existsByCustomer_Id(Long customerId);
}
