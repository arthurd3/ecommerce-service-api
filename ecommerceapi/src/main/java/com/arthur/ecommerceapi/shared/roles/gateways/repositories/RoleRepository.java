package com.arthur.ecommerceapi.shared.roles.gateways.repositories;

import com.arthur.ecommerceapi.customers.gateways.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
