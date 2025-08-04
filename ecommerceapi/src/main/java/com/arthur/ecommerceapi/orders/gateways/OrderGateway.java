package com.arthur.ecommerceapi.orders.gateways;

import com.arthur.ecommerceapi.orders.domain.model.Order;
import com.arthur.ecommerceapi.orders.gateways.entities.OrderEntity;

import java.util.UUID;

public interface OrderGateway {

    Order create(final OrderEntity order);

    Order findById(final UUID orderId);
}
