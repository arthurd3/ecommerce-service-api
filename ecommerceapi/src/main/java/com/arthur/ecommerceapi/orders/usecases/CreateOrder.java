package com.arthur.ecommerceapi.orders.usecases;

import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.orders.domain.model.Order;
import com.arthur.ecommerceapi.orders.dtos.request.OrderRequestDTO;
import com.arthur.ecommerceapi.orders.gateways.OrderGateway;
import com.arthur.ecommerceapi.orders.gateways.OrderSystemGateway;
import com.arthur.ecommerceapi.orders.gateways.entities.OrderEntity;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateOrder {

    private final OrderGateway orderGateway;
    private final OrderSystemGateway orderSystemGateway;

    @Transactional
    public Order create(final OrderRequestDTO dto){

        CustomerEntity customer = orderSystemGateway
                .findCustomerEntityById(dto.customerId());

        ProductEntity product = orderSystemGateway
                .findProductEntityById(dto.productId());

        OrderEntity orderSave = OrderEntity.createObj(product, customer, customer.getAddress(), dto.specification());

        return orderGateway.create(orderSave);
    }
}
