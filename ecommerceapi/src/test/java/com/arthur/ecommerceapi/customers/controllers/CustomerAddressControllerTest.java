package com.arthur.ecommerceapi.customers.controllers;

import com.arthur.ecommerceapi.customers.controllers.mappers.AddressMapper;
import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.dtos.request.AddressRequestDTO;
import com.arthur.ecommerceapi.customers.usecases.CreateAddress;
import com.arthur.ecommerceapi.customers.usecases.FindAddress;
import com.arthur.ecommerceapi.customers.usecases.UpdateAddress;
import com.arthur.ecommerceapi.orders.dtos.response.AddressResponseDTO;
import com.arthur.ecommerceapi.testFactory.builders.AddressTestBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.when;

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
            AddressRequestDTO addressRequest = AddressTestBuilder.anAddress()
                    .withCity("Juiz de Fora")
                    .withCustomer(null)
                    .withCountry("Brazil")
                    .withStreet("St francos")
                    .withZip("12345")
                    .withState("Minas Gerais")
                    .buildAddressRequestDTO();

            Address addressDomain = AddressTestBuilder.anAddress()
                    .withCustomer(null)
                    .withCountry("Brazil")
                    .withStreet("St francos")
                    .withZip("12345")
                    .withState("Minas Gerais")
                    .buildDomain();

            AddressResponseDTO responseAddressDto = AddressTestBuilder.anAddress()
                    .withCustomer(null)
                    .withCountry("Brazil")
                    .withStreet("St francos")
                    .withZip("12345")
                    .withState("Minas Gerais")
                    .buildAddressResponseDTO();

            when(mapper.toDomain(addressRequest)).thenReturn(addressDomain);
            when(createAddress.create(addressDomain)).



        }
    }

    @Test
    void update() {
    }

    @Test
    void findById() {
    }
}