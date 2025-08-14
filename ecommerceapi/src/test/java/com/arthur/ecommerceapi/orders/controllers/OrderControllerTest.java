package com.arthur.ecommerceapi.orders.controllers;

import com.arthur.ecommerceapi.customers.controllers.CustomerAddressController;
import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.exceptions.UserNotFoundException;
import com.arthur.ecommerceapi.orders.controllers.mapppers.OrderMapper;
import com.arthur.ecommerceapi.orders.domain.model.Order;
import com.arthur.ecommerceapi.orders.dtos.request.OrderRequestDTO;
import com.arthur.ecommerceapi.orders.dtos.response.AddressOrderResponseDTO;
import com.arthur.ecommerceapi.orders.dtos.response.CustomerOderResponseDTO;
import com.arthur.ecommerceapi.orders.dtos.response.OrderResponseDTO;
import com.arthur.ecommerceapi.orders.enums.OrderStatus;
import com.arthur.ecommerceapi.orders.usecases.CreateOrder;
import com.arthur.ecommerceapi.orders.usecases.FindOrder;
import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.dtos.response.ProductResponseDTO;
import com.arthur.ecommerceapi.products.exceptions.ProductNotFoundException;
import com.arthur.ecommerceapi.testFactory.DataTestFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateOrder createOrder;

    @MockBean
    private OrderMapper mapper;

    @MockBean
    private FindOrder findOrder;

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
            address.setId(1L);
            var product = DataTestFactory.createProduct();
            product.setId(UUID.randomUUID());

            final var ORDER_ID = UUID.randomUUID();

            createdOrder = Order.createOrder(ORDER_ID ,
                    product ,
                    customer ,
                    address ,
                    requestDto.specification() ,
                    OrderStatus.PENDING_PAYMENT );

            CustomerOderResponseDTO customerOderResponseDTO  = new CustomerOderResponseDTO(customer.getId() ,
                    customer.getName(),
                    customer.getEmail() ,
                    customer.getPhone());

            AddressOrderResponseDTO toAddressResponseDTO = new AddressOrderResponseDTO(address.getId() ,
                    address.getStreet() ,
                    address.getCity() ,
                    address.getState() ,
                    address.getZip() ,
                    address.getCountry());

            ProductResponseDTO productResponseDTO = new ProductResponseDTO(
                    product.getId() ,
                    product.getName() ,
                    product.getPrice().getFormatedValue() ,
                    product.getDescription() ,
                    product.getCategory() ,
                    product.getQuantity());

            responseDto = new OrderResponseDTO(createdOrder.getOrderId() ,
                    customerOderResponseDTO ,
                    toAddressResponseDTO ,
                    createdOrder.getSpecification() ,
                    productResponseDTO);
        }

        @Test
        @DisplayName("Should create order with success")
        void shouldCreateOrderWithSuccess() throws Exception {

            when(createOrder.create(requestDto)).thenReturn(createdOrder);
            when(mapper.toDto(createdOrder)).thenReturn(responseDto);

            mockMvc.perform(post("/api/v1/orders")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.oderId").value(createdOrder.getOrderId().toString()))
                .andExpect(jsonPath("$.customer.customerId").value(createdOrder.getCustomer().getId()))
                .andExpect(jsonPath("$.product.id").value(createdOrder.getProduct().getId().toString()))
                .andExpect(jsonPath("$.specification").value(createdOrder.getSpecification()))
                .andExpect(jsonPath("$.toAddress.addressId").value(createdOrder.getToAddress().getId()));

            verify(createOrder, times(1)).create(requestDto);
        }

        @DisplayName("Deve retornar 404 quando uma dependência não for encontrada")
        @ParameterizedTest
        @MethodSource("notFoundExceptions")
        void shouldReturn404WhenDependencyIsNotFound(RuntimeException exceptionToThrow, String expectedMessage) throws Exception {
            requestDto = new OrderRequestDTO("I HATE E2E TESTS", 999L, UUID.randomUUID());
            when(createOrder.create(requestDto)).thenThrow(exceptionToThrow);

            mockMvc.perform(post("/api/v1/orders")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestDto)))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.details").value(expectedMessage));
        }

        static Stream<Arguments> notFoundExceptions() {
            final Long FAKE_CUSTOMER_ID = 999L;
            final UUID FAKE_PRODUCT_ID = UUID.randomUUID();
            return Stream.of(
                    arguments(new UserNotFoundException("User not found with id: " + FAKE_CUSTOMER_ID), "User not found with id: " + FAKE_CUSTOMER_ID),
                    arguments(new ProductNotFoundException("Product with " + FAKE_PRODUCT_ID + " not found!"), "Product with " + FAKE_PRODUCT_ID + " not found!")
            );
        }

    }

    @Nested
    @DisplayName("GET /api/v1/orders/{id} - Find Order by Id")
    class findOrderByIdWithSuccess {

        @Test
        @DisplayName("Should find order by id with success")
        void shouldFindById() throws Exception {

            final var ORDER_ID = UUID.randomUUID();

            var orderFounded = Order.createOrder(ORDER_ID ,
                    new Product() ,
                    new Customer(),
                    new Address(),
                    "Sem pelicula" ,
                    OrderStatus.PENDING_PAYMENT );

            var responseDto = new OrderResponseDTO(orderFounded.getOrderId() ,
                    new CustomerOderResponseDTO(null , null , null , null) ,
                    new AddressOrderResponseDTO(null , null , null ,  null , null , null) ,
                    orderFounded.getSpecification() ,
                    new ProductResponseDTO(null , null, null , null , null, null));


            when(findOrder.findById(orderFounded.getOrderId())).thenReturn(orderFounded);
            when(mapper.toDto(orderFounded)).thenReturn(responseDto);

            mockMvc.perform(get("/api/v1/orders/{id}" , ORDER_ID)
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.oderId").value(orderFounded.getOrderId().toString()));
        }
    }
}