package com.arthur.ecommerceapi.products.usecases;

import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.gateways.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindProduct {

    private final ProductGateway productGateway;

    public Product findById(final UUID uuid) {
        return productGateway.findById(uuid);
    }
}
