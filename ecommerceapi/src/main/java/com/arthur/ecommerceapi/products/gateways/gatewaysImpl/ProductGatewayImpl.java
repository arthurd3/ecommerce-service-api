package com.arthur.ecommerceapi.products.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.gateways.ProductGateway;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;
import com.arthur.ecommerceapi.products.gateways.mappers.ProductGatewayMapper;
import com.arthur.ecommerceapi.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductGatewayImpl implements ProductGateway {

    private final ProductRepository repository;
    private final ProductGatewayMapper mapper;

    @Override
    public Product create(Product product) {
        ProductEntity savedProduct = repository.save(mapper.toEntity(product));
        return mapper.toDomain(savedProduct);
    }
}
