package com.arthur.ecommerceapi.orders.gateways;

import com.arthur.ecommerceapi.orders.domain.model.Order;
import com.arthur.ecommerceapi.orders.gateways.entities.OrderEntity;

public interface OrderGateway {

    Order create(final OrderEntity order);
}
