package com.arthur.ecommerceapi.products.usecases;

import com.arthur.ecommerceapi.products.gateways.ProductGateway;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindProductEntity {

    private final ProductGateway productGateway;

    public ProductEntity findById(final UUID productId) {
        return productGateway.findEntityById(productId);
    }
}
