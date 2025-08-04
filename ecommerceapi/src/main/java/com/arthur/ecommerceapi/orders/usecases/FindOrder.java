package com.arthur.ecommerceapi.orders.usecases;

import com.arthur.ecommerceapi.orders.domain.model.Order;
import com.arthur.ecommerceapi.orders.gateways.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindOrder {

    private final OrderGateway orderGateway;

    public Order findById(final UUID orderId){
        return orderGateway.findById(orderId);
    }
}
