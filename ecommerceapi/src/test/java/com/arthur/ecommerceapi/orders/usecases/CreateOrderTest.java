package com.arthur.ecommerceapi.orders.usecases;

import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.orders.gateways.OrderSystemGateway;
import com.arthur.ecommerceapi.orders.repositories.OrderRepository;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;
import com.arthur.ecommerceapi.testFactory.builders.AddressTestBuilder;
import com.arthur.ecommerceapi.testFactory.builders.CustomerTestBuilder;
import com.arthur.ecommerceapi.testFactory.builders.ProductTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateOrderTest {

    @InjectMocks
    private CreateOrder createOrder;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderSystemGateway  orderSystemGateway;

    private CustomerEntity customerEntity;
    private ProductEntity productEntity;
    private AddressEntity addressEntity;

    @BeforeEach
    void setUp() {
        customerEntity = CustomerTestBuilder.aCustomer().buildEntity();
        productEntity = ProductTestBuilder.aProduct().buildEntity();
        addressEntity = AddressTestBuilder.aAddress().buildEntity();
    }

    @Test
    void create() {



    }
}