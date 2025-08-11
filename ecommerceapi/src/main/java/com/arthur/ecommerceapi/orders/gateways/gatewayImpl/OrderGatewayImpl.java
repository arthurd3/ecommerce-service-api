package com.arthur.ecommerceapi.orders.gateways.gatewayImpl;

import com.arthur.ecommerceapi.customers.gateways.mappers.GatewayMapper;
import com.arthur.ecommerceapi.orders.domain.model.Order;
import com.arthur.ecommerceapi.orders.exceptions.OrderNotFoundExecption;
import com.arthur.ecommerceapi.orders.gateways.OrderGateway;
import com.arthur.ecommerceapi.orders.gateways.entities.OrderEntity;
import com.arthur.ecommerceapi.orders.repositories.OrderRepository;
import com.arthur.ecommerceapi.products.gateways.mappers.ProductGatewayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderGatewayImpl implements OrderGateway {

    private final OrderRepository repository;
    private final ProductGatewayMapper productMapper;
    private final GatewayMapper gatewayMapper;

    @Override
    public Order create(final OrderEntity order) {
        OrderEntity savedOrder = repository.save(order);

        productMapper.toDomain(order.getProduct());
                gatewayMapper.customerToDomain(order.getCustomer());
                gatewayMapper.addressToDomain(order.getToAddress());

        return orderToDomain(savedOrder);
    }

    @Override
    public Order findById(final UUID orderId) {
         return orderToDomain(repository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundExecption("Order with id "+ orderId +" not found!!")));
    }

    private Order orderToDomain(OrderEntity order) {
        return Order.createOrder(
                order.getId(),
                productMapper.toDomain(order.getProduct()),
                gatewayMapper.customerToDomain(order.getCustomer()),
                gatewayMapper.addressToDomain(order.getToAddress()),
                order.getSpecification(),
                order.getStatus()
        );
    }

}
