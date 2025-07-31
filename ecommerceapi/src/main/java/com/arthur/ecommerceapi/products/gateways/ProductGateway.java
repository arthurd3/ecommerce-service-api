package com.arthur.ecommerceapi.products.gateways;

import com.arthur.ecommerceapi.products.domain.models.Product;

import java.util.UUID;

public interface ProductGateway {

    Product create(final Product product);

    Product findById(final UUID uuid);

    void delete(final UUID uuid);

    Product update(final Product product);
}
