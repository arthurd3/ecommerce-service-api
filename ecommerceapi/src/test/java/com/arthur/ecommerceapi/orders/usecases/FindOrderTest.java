package com.arthur.ecommerceapi.orders.usecases;

import com.arthur.ecommerceapi.orders.domain.model.Order;
import com.arthur.ecommerceapi.orders.enums.OrderStatus;
import com.arthur.ecommerceapi.orders.exceptions.OrderNotFoundExecption;
import com.arthur.ecommerceapi.orders.gateways.OrderGateway;
import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.testFactory.builders.AddressTestBuilder;
import com.arthur.ecommerceapi.testFactory.builders.CustomerTestBuilder;
import com.arthur.ecommerceapi.testFactory.builders.ProductTestBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindOrderTest {

    @InjectMocks
    private FindOrder findOrder;

    @Mock
    private OrderGateway orderGateway;

    @Nested
    class FindOrderById{

        @Test
        @DisplayName("Should find order by id with success")
        void shouldFindOrderById() {

            var expectedProduct = ProductTestBuilder.aProduct()
                    .withId(UUID.randomUUID())
                    .withName("Product 1")
                    .withPrice(new Money("1222"))
                    .buildDomain();

            var expectedCustomer = CustomerTestBuilder.aCustomer()
                    .withId(1L)
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
                    "Sem pelicula",
                    OrderStatus.PENDING_PAYMENT
            );

            when(orderGateway.findById(expectedOrder.getOrderId())).thenReturn(expectedOrder);

            var findedOrder = findOrder.findById(expectedOrder.getOrderId());

            assertNotNull(findedOrder);
            assertEquals(expectedOrder.getOrderId(), findedOrder.getOrderId());
            assertEquals(expectedOrder.getToAddress() , findedOrder.getToAddress());
            assertEquals(expectedOrder.getCustomer(), findedOrder.getCustomer());
            assertEquals(expectedOrder.getStatus(), findedOrder.getStatus());
            assertEquals(expectedOrder.getProduct(), findedOrder.getProduct());

        }

        @Test
        @DisplayName("Should throw address not found exception")
        void shouldThrowAddressNotFoundException() {
            UUID notExistUUID = UUID.randomUUID();

            when(orderGateway.findById(notExistUUID))
                    .thenThrow(new OrderNotFoundExecption("Order with id "+ notExistUUID +" not found!!"));

            assertThrows(OrderNotFoundExecption.class,
                    () -> findOrder.findById(notExistUUID));

            verify(orderGateway , times(1)).findById(notExistUUID);
        }
    }
}