package com.arthur.ecommerceapi.orders.controllers;

import com.arthur.ecommerceapi.customers.controllers.CustomerAddressController;
import com.arthur.ecommerceapi.orders.controllers.mapppers.OrderMapper;
import com.arthur.ecommerceapi.orders.domain.model.Order;
import com.arthur.ecommerceapi.orders.dtos.request.OrderRequestDTO;
import com.arthur.ecommerceapi.orders.dtos.response.AddressOrderResponseDTO;
import com.arthur.ecommerceapi.orders.dtos.response.CustomerOderResponseDTO;
import com.arthur.ecommerceapi.orders.dtos.response.OrderResponseDTO;
import com.arthur.ecommerceapi.orders.enums.OrderStatus;
import com.arthur.ecommerceapi.orders.usecases.CreateOrder;
import com.arthur.ecommerceapi.orders.usecases.FindOrder;
import com.arthur.ecommerceapi.testFactory.DataTestFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(CustomerAddressController.class)
class OrderControllerTest {

    @Autowired
    private OrderController orderController;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CreateOrder createOrder;

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private FindOrder findOrder;

    @Autowired
    private MockMvc mockMvc;


    @Nested
    @DisplayName("POST /api/v1/orders - Create Order")
    class createOrderWithSuccess{

        private OrderRequestDTO requestDto;
        private Order createdOrder;
        private OrderResponseDTO responseDto;

        @BeforeEach
        void setUp(){
            requestDto = new OrderRequestDTO(
                    "I HATE E2E TESTS" ,
                    1L ,
                    UUID.randomUUID());

            var customer = DataTestFactory.createCustomer();
            customer.setId(1L);
            var address = DataTestFactory.createAddress();
            var product = DataTestFactory.createProduct();

            final var ORDER_ID = UUID.randomUUID();

            createdOrder = Order.createOrder(ORDER_ID ,
                    product ,
                    customer ,
                    address ,
                    requestDto.specification() ,
                    OrderStatus.PENDING_PAYMENT );

            CustomerOderResponseDTO customerOderResponseDTO  = new CustomerOderResponseDTO();
            AddressOrderResponseDTO toAddressResponseDTO = new AddressOrderResponseDTO();

            responseDto = new OrderResponseDTO()
        }


        @Test
        @DisplayName("Should create order with success")
        void shouldCreateOrderWithSuccess() throws Exception {


            when(createOrder.create(requestDto)).thenReturn(createdOrder);
            when(mapper.toDto(createdOrder)).thenReturn(responseDto);

        }

    }

    @Test
    void findById() {
    }
}