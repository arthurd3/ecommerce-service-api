package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.exceptions.UserNotFoundException;
import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
import com.arthur.ecommerceapi.customers.repositories.CustomerRepository;
import com.arthur.ecommerceapi.testFactory.DataTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindCustomerTest {

    @InjectMocks
    private FindCustomer findCustomer;

    @Mock
    private CustomerGateway customerGateway;

    @Nested
    class FindCustomerModel{

        private Customer customer;

        @BeforeEach
        void setUp() {
            customer = DataTestFactory.createCustomer();
        }

        @Test
        @DisplayName("Should find customer with success")
        public void shouldFindCustomerWithSuccess(){

            final Long customerId = customer.getId();
            when(customerGateway.findById(customerId)).thenReturn(customer);

            Customer foundCustomer = findCustomer.findById(customerId);

            assertNotNull(foundCustomer);
            assertEquals(customerId, foundCustomer.getId());
            assertEquals(customer.getName(), foundCustomer.getName());
            assertEquals(customer, foundCustomer);

            verify(customerGateway , times(1)).findById(customerId);
        }

        @Test
        @DisplayName("Should Throw User not Found Exception ")
        public void shouldThrowUserNotFoundException(){

            when(customerGateway.findById(customer.getId()))
                    .thenThrow(new UserNotFoundException("User not found with id: " + customer.getId()));

            UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
                findCustomer.findById(customer.getId());
            });

            assertEquals("User not found with id: " + customer.getId(), exception.getMessage());
            verify(customerGateway , times(1)).findById(customer.getId());
        }
    }
}