package com.arthur.ecommerceapi.products.usecases;

import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.gateways.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProduct {

    private final ProductGateway productGateway;

    public Product create(Product product) {
        return productGateway.create(product);
    }
}
