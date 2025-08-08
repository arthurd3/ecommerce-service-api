package com.arthur.ecommerceapi.customers.controllers;

import com.arthur.ecommerceapi.customers.controllers.mappers.AddressMapper;
import com.arthur.ecommerceapi.customers.dtos.request.AddressRequestDTO;
import com.arthur.ecommerceapi.customers.usecases.CreateAddress;
import com.arthur.ecommerceapi.customers.usecases.FindAddress;
import com.arthur.ecommerceapi.customers.usecases.UpdateAddress;
import com.arthur.ecommerceapi.testFactory.builders.AddressDTOBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CustomerAddressControllerTest.class)
class CustomerAddressControllerTest {

    @Autowired
    private AddressMapper mapper;

    @MockitoBean
    private CreateAddress createAddress;

    @MockitoBean
    private UpdateAddress updateAddress;

    @MockitoBean
    private FindAddress findAddress;

    @Nested
    @DisplayName("Create Address with success")
    class CreateAddressWithSuccess{

        @Test
        void create() {
            var addressRequest = AddressDTOBuilder.aAddressReqDTO().withCity("Juiz de Fora")
                    .withCustomerId(1L)
                    .withCountry("Brazil")
                    .withStreet("St Frangos")
                    .withZip("12345")
                    .buildAddressRequestDTO();

            


        }
    }

    @Test
    void update() {
    }

    @Test
    void findById() {
    }
}