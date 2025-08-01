package com.arthur.ecommerceapi.orders.gateways.gatewayImpl;

import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.customers.usecases.FindCustomerEntity;
import com.arthur.ecommerceapi.orders.gateways.OrderSystemGateway;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;
import com.arthur.ecommerceapi.products.usecases.FindProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderSystemGatewayImpl implements OrderSystemGateway {

    private final FindCustomerEntity findCustomerEntity;
    private final FindProductEntity findProductEntity;

    @Override
    public CustomerEntity findCustomerEntityById(final Long customerId) {
        return findCustomerEntity.findById(customerId);
    }

    @Override
    public ProductEntity findProductEntityById(final UUID productId) {
        return findProductEntity.findById(productId);
    }
}
