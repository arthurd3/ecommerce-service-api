package com.arthur.ecommerceapi.orders.repositories;

import com.arthur.ecommerceapi.orders.gateways.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
