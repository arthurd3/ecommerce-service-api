package com.arthur.ecommerceapi.customers.controllers;

import com.arthur.ecommerceapi.customers.controllers.mappers.CustomerMapper;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.dtos.request.CustomerRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.request.CustomerPutRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.response.CustomerResponseDTO;
import com.arthur.ecommerceapi.customers.exceptions.UserNotFoundException;
import com.arthur.ecommerceapi.customers.usecases.*;
import com.arthur.ecommerceapi.testFactory.builders.CustomerDTOTestBuilder;
import com.arthur.ecommerceapi.testFactory.builders.CustomerTestBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CustomerMapper mapper;

    @MockitoBean
    private CreateCustomer createCustomer;

    @MockitoBean
    private FindCustomer findCustomer;

    @MockitoBean
    private DeleteCustomer deleteCustomer;

    @MockitoBean
    private FindAllCustomers findAllCustomers;

    @MockitoBean
    private UpdateCustomer updateCustomer;

    @Nested
    @DisplayName("POST /api/v1/customer - Create Customer")
    class CreateCustomerEndpoint {

        @Test
        @DisplayName("Should create customer successfully and return 201 Created")
        void shouldCreateCustomerAndReturn201() throws Exception {
            CustomerRequestDTO requestDto = CustomerDTOTestBuilder.aCustomerDTO()
                    .withName("João Silva")
                    .withEmail("joao@test.com")
                    .withPhone("11987654321")
                    .buildRequestDTO();

            Customer domainCustomer = CustomerTestBuilder.aCustomer()
                    .withName("João Silva")
                    .withEmail("joao@test.com")
                    .buildDomain();

            Customer savedCustomer = CustomerTestBuilder.aCustomer()
                    .withId(1L)
                    .withName("João Silva")
                    .withEmail("joao@test.com")
                    .buildDomain();

            CustomerResponseDTO responseDto = CustomerDTOTestBuilder.aCustomerDTO()
                    .withId(1L)
                    .withName("João Silva")
                    .withEmail("joao@test.com")
                    .buildResponseDTO();

            when(mapper.toDomain(requestDto)).thenReturn(domainCustomer);
            when(createCustomer.create(domainCustomer)).thenReturn(savedCustomer);
            when(mapper.toDTO(savedCustomer)).thenReturn(responseDto);

            mockMvc.perform(post("/api/v1/customer")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestDto)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(1L))
                    .andExpect(jsonPath("$.name").value("João Silva"))
                    .andExpect(jsonPath("$.email").value("joao@test.com"));

            verify(mapper).toDomain(requestDto);
            verify(createCustomer).create(domainCustomer);
            verify(mapper).toDTO(savedCustomer);
        }

        @Test
        @DisplayName("Should return 400 Bad Request when request body is invalid")
        void shouldReturn400WhenRequestBodyIsInvalid() throws Exception {
            CustomerRequestDTO invalidRequest = new CustomerRequestDTO("", "", "", "");

            mockMvc.perform(post("/api/v1/customer")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(invalidRequest)))
                    .andExpect(status().isBadRequest());

            verify(createCustomer, never()).create(any());
        }
    }

    @Nested
    @DisplayName("GET /api/v1/customer - Find All Customers")
    class FindAllCustomersEndpoint {

        @Test
        @DisplayName("Should return paginated customers successfully")
        void shouldReturnPaginatedCustomers() throws Exception {
            List<Customer> customers = Arrays.asList(
                    CustomerTestBuilder.aCustomer().withId(1L).withName("Customer 1").buildDomain(),
                    CustomerTestBuilder.aCustomer().withId(2L).withName("Customer 2").buildDomain()
            );
            
            Page<Customer> customerPage = new PageImpl<>(customers, PageRequest.of(0, 10), 2);

            when(findAllCustomers.findAll(any(PageRequest.class))).thenReturn(customerPage);

            mockMvc.perform(get("/api/v1/customer")
                            .param("page", "0")
                            .param("size", "10"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content").isArray())
                    .andExpect(jsonPath("$.content.length()").value(2))
                    .andExpect(jsonPath("$.totalElements").value(2));

            verify(findAllCustomers).findAll(PageRequest.of(0, 10));
        }

        @Test
        @DisplayName("Should return 400 for invalid pagination parameters")
        void shouldReturn400ForInvalidPagination() throws Exception {
            mockMvc.perform(get("/api/v1/customer")
                            .param("page", "-1") 
                            .param("size", "0"))  
                    .andExpect(status().isBadRequest());

            verify(findAllCustomers, never()).findAll(any());
        }
    }

    @Nested
    @DisplayName("GET /api/v1/customer/{id} - Find Customer By ID")
    class FindCustomerByIdEndpoint {

        @Test
        @DisplayName("Should return customer when ID exists")
        void shouldReturnCustomerWhenIdExists() throws Exception {
            Long customerId = 1L;
            Customer existingCustomer = CustomerTestBuilder.aCustomer()
                    .withId(customerId)
                    .withName("João Silva")
                    .withEmail("joao@test.com")
                    .buildDomain();

            CustomerResponseDTO responseDto = CustomerDTOTestBuilder.aCustomerDTO()
                    .withId(customerId)
                    .withName("João Silva")
                    .withEmail("joao@test.com")
                    .buildResponseDTO();

            when(findCustomer.findById(customerId)).thenReturn(existingCustomer);
            when(mapper.toDTO(existingCustomer)).thenReturn(responseDto);

            mockMvc.perform(get("/api/v1/customer/{id}", customerId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(customerId))
                    .andExpect(jsonPath("$.name").value("João Silva"))
                    .andExpect(jsonPath("$.email").value("joao@test.com"));

            verify(findCustomer).findById(customerId);
            verify(mapper).toDTO(existingCustomer);
        }

        @Test
        @DisplayName("Should return 404 when customer not found")
        void shouldReturn404WhenCustomerNotFound() throws Exception {
            Long nonExistentId = 999L;

            when(findCustomer.findById(nonExistentId))
                    .thenThrow(new UserNotFoundException("User not found with id: " + nonExistentId));

            mockMvc.perform(get("/api/v1/customer/{id}", nonExistentId))
                    .andExpect(status().isNotFound());

            verify(findCustomer).findById(nonExistentId);
            verify(mapper, never()).toDTO(any());
        }
    }

    @Nested
    @DisplayName("DELETE /api/v1/customer/{id} - Delete Customer")
    class DeleteCustomerEndpoint {

        @Test
        @DisplayName("Should delete customer successfully and return 204 No Content")
        void shouldDeleteCustomerSuccessfully() throws Exception {
            Long customerId = 1L;

            doNothing().when(deleteCustomer).delete(customerId);

            mockMvc.perform(delete("/api/v1/customer/{id}", customerId))
                    .andExpect(status().isNoContent());

            verify(deleteCustomer).delete(customerId);
        }

        @Test
        @DisplayName("Should return 404 when trying to delete non-existent customer")
        void shouldReturn404WhenDeletingNonExistentCustomer() throws Exception {
            Long nonExistentId = 999L;

            doThrow(new UserNotFoundException("User not found with id: " + nonExistentId))
                    .when(deleteCustomer).delete(nonExistentId);

            mockMvc.perform(delete("/api/v1/customer/{id}", nonExistentId))
                    .andExpect(status().isNotFound());

            verify(deleteCustomer).delete(nonExistentId);
        }
    }

    @Nested
    @DisplayName("PUT /api/v1/customer/{id} - Update Customer")
    class UpdateCustomerEndpoint {

        @Test
        @DisplayName("Should update customer successfully and return 200 OK")
        void shouldUpdateCustomerSuccessfully() throws Exception {
            Long customerId = 1L;
            CustomerPutRequestDTO putRequestDto = CustomerDTOTestBuilder.aCustomerDTO()
                    .withName("João Silva Updated")
                    .withEmail("joao.updated@test.com")
                    .withPhone("11999999999")
                    .buildPutRequestDTO();

            Customer domainCustomer = CustomerTestBuilder.aCustomer()
                    .withId(customerId)
                    .withName("João Silva Updated")
                    .withEmail("joao.updated@test.com")
                    .buildDomain();

            Customer updatedCustomer = CustomerTestBuilder.aCustomer()
                    .withId(customerId)
                    .withName("João Silva Updated")
                    .withEmail("joao.updated@test.com")
                    .buildDomain();

            CustomerResponseDTO responseDto = CustomerDTOTestBuilder.aCustomerDTO()
                    .withId(customerId)
                    .withName("João Silva Updated")
                    .withEmail("joao.updated@test.com")
                    .buildResponseDTO();

            when(mapper.updateFromDTO(putRequestDto, customerId)).thenReturn(domainCustomer);
            when(updateCustomer.update(domainCustomer)).thenReturn(updatedCustomer);
            when(mapper.toDTO(updatedCustomer)).thenReturn(responseDto);

            mockMvc.perform(put("/api/v1/customer/{id}", customerId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(putRequestDto)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(customerId))
                    .andExpect(jsonPath("$.name").value("João Silva Updated"))
                    .andExpect(jsonPath("$.email").value("joao.updated@test.com"));

            verify(mapper).updateFromDTO(putRequestDto, customerId);
            verify(updateCustomer).update(domainCustomer);
            verify(mapper).toDTO(updatedCustomer);
        }

        @Test
        @DisplayName("Should return 404 when updating non-existent customer")
        void shouldReturn404WhenUpdatingNonExistentCustomer() throws Exception {
            Long nonExistentId = 999L;
            CustomerPutRequestDTO putRequestDto = CustomerDTOTestBuilder.aCustomerDTO()
                    .withName("Updated Name")
                    .buildPutRequestDTO();

            Customer domainCustomer = CustomerTestBuilder.aCustomer()
                    .withId(nonExistentId)
                    .buildDomain();

            when(mapper.updateFromDTO(putRequestDto, nonExistentId)).thenReturn(domainCustomer);
            when(updateCustomer.update(domainCustomer))
                    .thenThrow(new UserNotFoundException("User not found with id: " + nonExistentId));

            mockMvc.perform(put("/api/v1/customer/{id}", nonExistentId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(putRequestDto)))
                    .andExpect(status().isNotFound());

            verify(updateCustomer).update(domainCustomer);
        }
    }
}