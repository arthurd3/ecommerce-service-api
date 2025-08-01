package com.arthur.ecommerceapi.products.repositories;

import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findById(final UUID uuid);

    void deleteById(final UUID uuid);

}
