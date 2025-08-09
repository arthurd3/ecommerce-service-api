package com.arthur.ecommerceapi.customers.controllers;

import com.arthur.ecommerceapi.customers.controllers.mappers.AddressMapper;
import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.dtos.request.AddressPutRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.request.AddressRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.response.AddressResponseDTO;
import com.arthur.ecommerceapi.customers.exceptions.UserNotFoundException;
import com.arthur.ecommerceapi.customers.usecases.CreateAddress;
import com.arthur.ecommerceapi.customers.usecases.FindAddress;
import com.arthur.ecommerceapi.customers.usecases.FindCustomer;
import com.arthur.ecommerceapi.customers.usecases.UpdateAddress;
import com.arthur.ecommerceapi.testFactory.builders.AddressTestBuilder;
import com.arthur.ecommerceapi.testFactory.builders.CustomerTestBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerAddressController.class)
class CustomerAddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AddressMapper addressMapper;

    @MockitoBean
    private CreateAddress createAddress;

    @MockitoBean
    private FindCustomer findCustomer;

    @MockitoBean
    private UpdateAddress updateAddress;

    @MockitoBean
    private FindAddress findAddress;

    private static final Long CUSTOMER_ID = 1L;
    private static final Long ADDRESS_ID = 1L;

    @Nested
    @DisplayName("POST /api/v1/address/customer/{id} - Create Address")
    class CreateAddressEndpoint {

        private AddressRequestDTO validRequest;

        private Address domainAddress;

        private Address savedAddress;

        private AddressResponseDTO expectedResponse;

        @BeforeEach
        void setUp() {
            validRequest = AddressTestBuilder.anAddress()
                    .withStreet("Rua das Flores, 123")
                    .withCity("São Paulo")
                    .withState("SP")
                    .withZip("01234-567")
                    .withCountry("Brazil")
                    .buildAddressRequestDTO();

            domainAddress = AddressTestBuilder.anAddress()
                    .withStreet("Rua das Flores, 123")
                    .withCity("São Paulo")
                    .withState("SP")
                    .withZip("01234-567")
                    .withCountry("Brazil")
                    .withCustomer(CustomerTestBuilder.aCustomer()
                            .withId(CUSTOMER_ID)
                            .buildDomain())
                    .buildDomain();

            savedAddress = AddressTestBuilder.anAddress()
                    .withId(ADDRESS_ID)
                    .withStreet("Rua das Flores, 123")
                    .withCity("São Paulo")
                    .withState("SP")
                    .withZip("01234-567")
                    .withCountry("Brazil")
                    .withCustomer(CustomerTestBuilder.aCustomer()
                            .withId(CUSTOMER_ID)
                            .buildDomain())
                    .buildDomain();

            expectedResponse = AddressTestBuilder.anAddress()
                    .withId(ADDRESS_ID)
                    .withStreet("Rua das Flores, 123")
                    .withCity("São Paulo")
                    .withState("SP")
                    .withZip("01234-567")
                    .withCountry("Brazil")
                    .buildAddressResponseDTO();
        }

        @Test
        @DisplayName("Should create address successfully")
        void shouldCreateAddressSuccessfully() throws Exception {
            when(addressMapper.toDomain(validRequest)).thenReturn(domainAddress);
            when(createAddress.create(domainAddress, CUSTOMER_ID)).thenReturn(savedAddress);
            when(addressMapper.toDTO(savedAddress)).thenReturn(expectedResponse);

            mockMvc.perform(post("/api/v1/address/customer/{id}", CUSTOMER_ID)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(validRequest)))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(ADDRESS_ID))
                    .andExpect(jsonPath("$.street").value("Rua das Flores, 123"))
                    .andExpect(jsonPath("$.city").value("São Paulo"))
                    .andExpect(jsonPath("$.state").value("SP"))
                    .andExpect(jsonPath("$.zip").value("01234-567"))
                    .andExpect(jsonPath("$.country").value("Brazil"));

            verify(addressMapper).toDomain(validRequest);
            verify(createAddress).create(domainAddress, CUSTOMER_ID);
            verify(addressMapper).toDTO(savedAddress);
        }

        @Test
        @DisplayName("Should return 404 error on create with blank attribute")
        void shouldReturn400BadRequestWhenRequiredAttributeIsBlank() throws Exception {
            var invalidRequest = AddressTestBuilder.anAddress()
                    .withStreet("Rua das Flores, 123")
                    .withCity("")
                    .withState("SP")
                    .withZip(null)
                    .withCountry("Brazil")
                    .buildAddressRequestDTO();

            mockMvc.perform(post("/api/v1/address/customer/{id}" , CUSTOMER_ID)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(invalidRequest)))
                    .andExpect(status().isBadRequest());

            verify(createAddress, never()).create(any() , any());
        }


        @Test
        @DisplayName("Should Return 404 With Customer Not Exists")
        void shouldReturn404WithCustomerNotExists() throws Exception {
                final Long INVALID_ID = 999L;

                when(addressMapper.toDomain(validRequest)).thenReturn(domainAddress);
                when(createAddress.create(any(Address.class), eq(INVALID_ID)))
                        .thenThrow(new UserNotFoundException("User not found with id: " + INVALID_ID));

                mockMvc.perform(post("/api/v1/address/customer/{id}", INVALID_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(validRequest)))
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.status").value(404))
                        .andExpect(jsonPath("$.details").value("User not found with id: " + INVALID_ID));

                verify(createAddress).create(any(Address.class), eq(INVALID_ID));
        }

    }

    @Nested
    @DisplayName("PUT /api/v1/address/customer/{id} - Update Address")
    class UpdateAddressEndpoint {

        private AddressPutRequestDTO putRequestDTO;
        private Address domainAddress;
        private Address updatedAddress;
        private AddressResponseDTO  expectedResponse;

        @BeforeEach
        void setUp() {
            putRequestDTO = AddressTestBuilder.anAddress()
                    .withCity("Juiz de Updated")
                    .withCountry("Portugal Updated")
                    .withState("Minas Unicas Updated")
                    .withZip("01234-567")
                    .buildAddressPutRequestDTO();

            domainAddress = AddressTestBuilder.anAddress()
                    .withId(ADDRESS_ID)
                    .withCity("Juiz de Updated")
                    .withCountry("Portugal Updated")
                    .withState("Minas Unicas Updated")
                    .withZip("01234-567")
                    .buildDomain();

            updatedAddress = AddressTestBuilder.anAddress()
                    .withId(ADDRESS_ID)
                    .withCity("Juiz de Updated")
                    .withStreet("Rua das Flores, 123")
                    .withCountry("Portugal Updated")
                    .withState("Minas Unicas Updated")
                    .withZip("01234-567")
                    .buildDomain();

            expectedResponse = AddressTestBuilder.anAddress()
                    .withId(ADDRESS_ID)
                    .withCity("Juiz de Updated")
                    .withStreet("Rua das Flores, 123")
                    .withCountry("Portugal Updated")
                    .withState("Minas Unicas Updated")
                    .withZip("01234-567")
                    .buildAddressResponseDTO();

        }

        @Test
        @DisplayName("Should update address with success")
        void shouldUpdateAddressWithSuccess() throws Exception {

            when(addressMapper.updateFromDTO(putRequestDTO , ADDRESS_ID)).thenReturn(domainAddress);
            when(updateAddress.update(domainAddress)).thenReturn(updatedAddress);
            when(addressMapper.toDTO(updatedAddress)).thenReturn(expectedResponse);

            mockMvc.perform(put("/api/v1/address/customer/{id}" , CUSTOMER_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(putRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ADDRESS_ID))
                .andExpect(jsonPath("$.state").value(putRequestDTO.state()))
                .andExpect(jsonPath("$.street").value(updatedAddress.getStreet()))
                .andExpect(jsonPath("$.city").value(putRequestDTO.city()));

            verify(addressMapper).updateFromDTO(putRequestDTO , ADDRESS_ID);
            verify(updateAddress).update(domainAddress);
        }


        @Test
        @DisplayName("Should update address with success")
        void shouldReturn404WithCustomerNotExists() throws Exception {
            final long INVALID_USER_ID = 33333L;

            when(addressMapper.updateFromDTO(putRequestDTO , ADDRESS_ID)).thenReturn(domainAddress);
            when(updateAddress.update(domainAddress))
                    .thenThrow(new UserNotFoundException("User not found with id: " + INVALID_USER_ID));

            mockMvc.perform(put("/api/v1/address/customer/{id}" , CUSTOMER_ID)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(putRequestDTO)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.details").value("User not found with id: " + INVALID_USER_ID));

            verify(updateAddress).update(any(Address.class));
        }
    }

    @Test
    void findById() {
    }
}