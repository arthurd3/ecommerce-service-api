package com.arthur.ecommerceapi.orders.gateways.gatewayImpl;

import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.customers.repositories.CustomerRepository;
import com.arthur.ecommerceapi.orders.domain.model.Order;
import com.arthur.ecommerceapi.orders.enums.OrderStatus;
import com.arthur.ecommerceapi.orders.exceptions.OrderNotFoundExecption;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class OrderGatewayImplTest {

    @Autowired
    private OrderGateway orderGateway;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;

    private ProductEntity productEntity;
    private CustomerEntity customerEntity;
    private AddressEntity addressEntity;
    private OrderEntity orderExists;

    @BeforeEach
    void setUp() {

        customerEntity = CustomerTestBuilder.aCustomer()
                .withName("John")
                .withEmail("john@gmail.com")
                .withPhone("123456789")
                .buildEntity();

        productEntity = ProductTestBuilder.aProduct()
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

        customerEntity.setAddress(addressEntity);
        addressEntity.setCustomer(customerEntity);

        customerRepository.save(customerEntity);
        productRepository.save(productEntity);

        entityManager.flush();

        orderExists = OrderEntity.createObj(
                productEntity ,
                customerEntity,
                addressEntity,
                "I Hate integration tests");
    }

    @Nested
    class createOrderWithSuccess{

        @Test
        @DisplayName("Should create order with success")
        void shouldCreateOrderWithSuccess() {

            var orderToCreate = OrderEntity.createObj(
                    productEntity ,
                    customerEntity,
                    addressEntity,
                    "I Hate integration tests");


            Order savedOrder = orderGateway.create(orderToCreate);

            entityManager.flush();

            assertNotNull(savedOrder);
            assertNotNull(savedOrder.getOrderId());
            assertEquals(OrderStatus.PENDING_PAYMENT, savedOrder.getStatus());
            assertEquals(savedOrder.getCustomer().getEmail(), orderToCreate.getCustomer().getEmail());
            assertEquals(savedOrder.getCustomer().getPhone(), orderToCreate.getCustomer().getPhone());
            assertEquals(savedOrder.getToAddress().getId() , orderToCreate.getToAddress().getId());
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

    @Nested
    class findOrderByIdWithSuccess{

        @Test
        @DisplayName("Should find order by id with success")
        void shouldFindOrderById() {

            orderRepository.save(orderExists);
            entityManager.flush();

            var order = orderRepository
                    .findById(orderExists.getId()).get();

            assertNotNull(order);
            assertEquals(orderExists.getId(), order.getId());
            assertEquals(orderExists.getProduct().getId(), order.getProduct().getId());
            assertEquals(orderExists.getCustomer().getId(), order.getCustomer().getId());
        }

        @Test
        @DisplayName("Should Throw Order Not Found Exception On Find Order By Id")
        void shouldThrowOrderNotFoundExceptionOnFindOrderById() {
            final UUID ORDER_FAKE_ID = UUID.randomUUID();

            assertThrows(OrderNotFoundExecption.class ,
                    () -> orderGateway.findById(ORDER_FAKE_ID));
        }
    }
}