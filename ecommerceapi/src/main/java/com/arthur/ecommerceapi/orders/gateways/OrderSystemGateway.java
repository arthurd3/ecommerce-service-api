package com.arthur.ecommerceapi.orders.gateways;

import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;

import java.util.UUID;

public interface OrderSystemGateway {

     CustomerEntity findCustomerEntityById(final Long id);
     ProductEntity findProductEntityById(final UUID id);

}
