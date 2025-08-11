package com.arthur.ecommerceapi.orders.gateways.gatewayImpl;

import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.customers.repositories.CustomerRepository;
import com.arthur.ecommerceapi.orders.enums.OrderStatus;
import com.arthur.ecommerceapi.orders.gateways.OrderGateway;
import com.arthur.ecommerceapi.orders.gateways.entities.OrderEntity;
import com.arthur.ecommerceapi.orders.repositories.OrderRepository;
import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;
import com.arthur.ecommerceapi.products.repositories.ProductRepository;
import com.arthur.ecommerceapi.testFactory.builders.AddressTestBuilder;
import com.arthur.ecommerceapi.testFactory.builders.CustomerTestBuilder;
import com.arthur.ecommerceapi.testFactory.builders.ProductTestBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.persistence.EntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderGatewayImplTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;



    @Nested
    class createOrderWithSuccess{

        @Test
        @DisplayName("Should create order with success")
        void shouldCreateOrderWithSuccess() {

            CustomerEntity customerEntity = CustomerTestBuilder.aCustomer()
                    .withName("John")
                    .withEmail("john@gmail.com")
                    .withPhone("123456789")
                    .buildEntity();

            ProductEntity productEntity = ProductTestBuilder.aProduct()
                    .withName("Product 1")
                    .withAvailableToDiscount(true)
                    .withCategory(ProductCategory.ELECTRONICS)
                    .withDescription("Product 1 from Electronics")
                    .withQuantity(1000)
                    .withPrice(new Money("199"))
                    .buildEntity();

            AddressEntity addressEntity = AddressTestBuilder.aAddress()
                    .withStreet("123 Main St")
                    .withCity("New York")
                    .withCustomerEntity(customerEntity)
                    .withCountry("Australia")
                    .buildEntity();

            OrderEntity orderExists = OrderEntity.createObj(
                    productEntity ,
                    customerEntity ,
                    addressEntity ,
                    "I Hate integration tests");


            customerEntity.setAddress(addressEntity);
            addressEntity.setCustomer(customerEntity);

            customerRepository.save(customerEntity);
            productRepository.save(productEntity);


            OrderEntity savedOrder = orderRepository.save(orderExists);

            entityManager.flush();

            assertNotNull(savedOrder);
            assertNotNull(savedOrder.getId());
            assertEquals(OrderStatus.PENDING_PAYMENT, savedOrder.getStatus());
            assertEquals(productEntity, savedOrder.getProduct());

        }

        @Test
        @DisplayName("Should throw DataIntegrityViolationException error on create order")
        void shouldThrowDataIntegrityViolationExceptionOnCreateOrder() {
            
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        OrderEntity.createObj(
                                null ,
                                null ,
                                null ,
                                "I Hate integration tests");
                    }
            );

            assertNotNull(exception.getMessage());
        }

    }

    @Test
    void findById() {
    }
}