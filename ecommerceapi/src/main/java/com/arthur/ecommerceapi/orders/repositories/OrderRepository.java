package com.arthur.ecommerceapi.orders.repositories;

import com.arthur.ecommerceapi.orders.gateways.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
