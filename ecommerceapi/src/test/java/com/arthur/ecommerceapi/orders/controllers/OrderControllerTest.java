package com.arthur.ecommerceapi.orders.controllers;

import com.arthur.ecommerceapi.customers.controllers.CustomerAddressController;
import com.arthur.ecommerceapi.orders.controllers.mapppers.OrderMapper;
import com.arthur.ecommerceapi.orders.usecases.CreateOrder;
import com.arthur.ecommerceapi.orders.usecases.FindOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

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
    
    @Test
    void create() {
    }

    @Test
    void findById() {
    }
}