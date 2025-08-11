package com.arthur.ecommerceapi.orders.usecases;

import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.orders.domain.model.Order;
import com.arthur.ecommerceapi.orders.dtos.request.OrderRequestDTO;
import com.arthur.ecommerceapi.orders.enums.OrderStatus;
import com.arthur.ecommerceapi.orders.gateways.OrderGateway;
import com.arthur.ecommerceapi.orders.gateways.OrderSystemGateway;
import com.arthur.ecommerceapi.orders.gateways.entities.OrderEntity;

import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;
import com.arthur.ecommerceapi.testFactory.builders.AddressTestBuilder;
import com.arthur.ecommerceapi.testFactory.builders.CustomerTestBuilder;
import com.arthur.ecommerceapi.testFactory.builders.ProductTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderTest {

    @InjectMocks
    private CreateOrder createOrder;

    @Mock
    private OrderGateway orderGateway;

    @Mock
    private OrderSystemGateway orderSystemGateway;

    private CustomerEntity customerEntity;
    private ProductEntity productEntity;

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

        AddressEntity addressEntity = AddressTestBuilder.aAddress()
                .withStreet("123 Main St")
                .withCity("New York")
                .withCustomerEntity(customerEntity)
                .withCountry("Australia")
                .buildEntity();

        customerEntity.setAddress(addressEntity);
    }

    @Nested
    @DisplayName("Create Order with Successfully")
    class CreateOrderSuccess {

        @Test
        @DisplayName("Should create order with successfully")
        void shouldCreateOrderSuccessfully() {

            OrderRequestDTO dto = new OrderRequestDTO("Sem pelicula",
                    customerEntity.getId(),
                    productEntity.getId());

            var expectedProduct = ProductTestBuilder.aProduct()
                    .withId(productEntity.getId())
                    .withName("Product 1")
                    .withPrice(new Money(productEntity.getPrice().toString()))
                    .buildDomain();

            var expectedCustomer = CustomerTestBuilder.aCustomer()
                    .withId(customerEntity.getId())
                    .withName("John")
                    .buildDomain();

            var expectedAddress = AddressTestBuilder.aAddress()
                    .withStreet("123 Main St")
                    .withCity("New York")
                    .buildDomain();

            var expectedOrder = Order.createOrder(
                    UUID.randomUUID(),
                    expectedProduct,
                    expectedCustomer,
                    expectedAddress,
                    dto.specification(),
                    OrderStatus.PENDING_PAYMENT
            );

            when(orderSystemGateway.findCustomerEntityById(customerEntity.getId())).thenReturn(customerEntity);
            when(orderSystemGateway.findProductEntityById(productEntity.getId())).thenReturn(productEntity);
            when(orderGateway.create(any(OrderEntity.class))).thenReturn(expectedOrder);

            Order createdOrder = createOrder.create(dto);

            assertNotNull(createdOrder);
            assertEquals(expectedProduct.getId(), createdOrder.getProduct().getId());
            assertEquals(expectedCustomer.getId(), createdOrder.getCustomer().getId());
            assertEquals(expectedAddress.getStreet(), createdOrder.getToAddress().getStreet());
            assertEquals(dto.specification(), createdOrder.getSpecification());

            verify(orderSystemGateway).findCustomerEntityById(customerEntity.getId());
            verify(orderSystemGateway).findProductEntityById(productEntity.getId());
            verify(orderGateway).create(any(OrderEntity.class));
            
        }
    }
}