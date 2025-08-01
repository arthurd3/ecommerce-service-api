package com.arthur.ecommerceapi.orders.gateways;

import com.arthur.ecommerceapi.orders.domain.model.Order;

public interface OrderGateway {

    Order create(final Order order);
}
