package com.arthur.ecommerceapi.orders.gateways.gatewayImpl;

import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
import com.arthur.ecommerceapi.customers.gateways.mappers.GatewayMapper;
import com.arthur.ecommerceapi.orders.domain.model.Order;
import com.arthur.ecommerceapi.orders.gateways.OrderGateway;
import com.arthur.ecommerceapi.orders.gateways.entities.OrderEntity;
import com.arthur.ecommerceapi.orders.repositories.OrderRepository;
import com.arthur.ecommerceapi.products.controllers.mappers.ProductMapper;
import com.arthur.ecommerceapi.products.gateways.ProductGateway;
import com.arthur.ecommerceapi.products.gateways.mappers.ProductGatewayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderGatewayImpl implements OrderGateway {

    private final OrderRepository repository;
    private final ProductGatewayMapper productMapper;
    private final GatewayMapper gatewayMapper;

    @Override
    public Order create(final OrderEntity order) {

        repository.save(order);

        return Order.reconstitute(
                order.getId(),
                productMapper.toDomain(order.getProduct()),
                gatewayMapper.customerToDomain(order.getCustomer()),
                gatewayMapper.addressToDomain(order.getToAddress()),
                order.getSpecification(),
                order.getStatus()
        );
    }



}
