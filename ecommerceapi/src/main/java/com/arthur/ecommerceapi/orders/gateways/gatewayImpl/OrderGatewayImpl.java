package com.arthur.ecommerceapi.orders.gateways.gatewayImpl;

import com.arthur.ecommerceapi.orders.domain.model.Order;
import com.arthur.ecommerceapi.orders.gateways.OrderGateway;
import com.arthur.ecommerceapi.orders.gateways.entities.OrderEntity;
import com.arthur.ecommerceapi.orders.gateways.mappers.OrderGatewayMapper;
import com.arthur.ecommerceapi.orders.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderGatewayImpl implements OrderGateway {

    private final OrderRepository repository;
    private final OrderGatewayMapper mapper;

    @Override
    public Order create(Order order) {
        OrderEntity orderEntity = mapper.toEntity(order);
        repository.save(orderEntity);
        return order;
    }

}
