package com.arthur.ecommerceapi.orders.usecases;

import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.orders.gateways.OrderSystemGateway;
import com.arthur.ecommerceapi.orders.repositories.OrderRepository;
import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
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

import java.util.UUID;

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
        
        customerEntity = CustomerTestBuilder.aCustomer()
                .withId(1L)
                .withName("John")
                .withEmail("john@gmail.com")
                .withPhone("123456789")
            .buildEntity();

        productEntity = ProductTestBuilder.aProduct()
                .withId(UUID.randomUUID())
                .withName("Product 1")
                .withAvailableToDiscount(true)
                .withCategory(ProductCategory.ELECTRONICS)
                .withDescription("Product 1 from Electronics")
                .withQuantity(1000)
                .withPrice(new Money("199"))
            .buildEntity();

        addressEntity = AddressTestBuilder.aAddress()
                .withStreet("123 Main St")
                .withCity("New York")
                .withCustomerEntity(customerEntity)
                .withCountry("Australia")
            .buildEntity();
    }

    @Test
    void create() {



    }
}