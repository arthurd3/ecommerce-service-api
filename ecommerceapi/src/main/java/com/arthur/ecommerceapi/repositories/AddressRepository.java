package com.arthur.ecommerceapi.repositories;

import com.arthur.ecommerceapi.gateways.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    Boolean existsByCustomer_Id(Long customerId);
}
