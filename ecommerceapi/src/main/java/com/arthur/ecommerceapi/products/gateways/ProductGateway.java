package com.arthur.ecommerceapi.products.gateways;

import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;

import java.util.UUID;

public interface ProductGateway {

    Product create(final Product product);

    Product findById(final UUID uuid);

    void delete(final UUID uuid);

    Product update(final Product product);

    ProductEntity findEntityById(final UUID uuid);

    boolean exists(final UUID uuid);

}
