package com.arthur.ecommerceapi.products.repositories;

import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
