package com.arthur.ecommerceapi.products.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.exceptions.ProductNotFoundException;
import com.arthur.ecommerceapi.products.gateways.ProductGateway;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;
import com.arthur.ecommerceapi.products.gateways.mappers.ProductGatewayMapper;
import com.arthur.ecommerceapi.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductGatewayImpl implements ProductGateway {

    private final ProductRepository repository;
    private final ProductGatewayMapper mapper;

    @Override
    public Product create(final Product product) {
        ProductEntity savedProduct = repository.save(mapper.toEntity(product));
        return mapper.toDomain(savedProduct);
    }

    @Override
    public Product findById(UUID uuid) {
        return mapper.toDomain(repository.findById(uuid)
                .orElseThrow(() -> new ProductNotFoundException("Product with "+ uuid +" not found!")));
    }

    @Override
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    public Product update(Product product) {
        ProductEntity productEntity = this.findEntityById(product.getId());

        mapper.editEntityFromDomain(product, productEntity);

        return mapper.toDomain(productEntity);
    }

    @Override
    public ProductEntity findEntityById(UUID uuid) {
        return repository.findById(uuid)
                .orElseThrow(() -> new ProductNotFoundException("Product with "+ uuid +" not found!"));
    }
}
