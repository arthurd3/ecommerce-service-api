package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.exceptions.UserNotFoundException;
import com.arthur.ecommerceapi.customers.gateways.AddressGateway;
import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.testFactory.DataTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UpdateCustomerTest {

    @InjectMocks
    private UpdateCustomer updateCustomer;

    @Mock
    private CustomerGateway customerGateway;

    @Mock
    private ValidatorCustomer validatorCustomer;

    @Nested
    class updateCustomer{

        private Customer customer;

        @BeforeEach
        void setUp() {
            customer = DataTestFactory.createCustomer();
        }

        @Test
        @DisplayName("Should create customer with success")
        public void shouldCreateCustomerWithSuccess(){
            when(customerGateway.update(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

            Customer updatedCustomer = updateCustomer.update(customer);

            assertNotNull(updatedCustomer);
            assertEquals(customer.getId(), updatedCustomer.getId());
            assertEquals(customer.getName(), updatedCustomer.getName());
            assertEquals(customer.getEmail(), updatedCustomer.getEmail());

            verify(validatorCustomer, times(1)).validate(customer);
            verify(customerGateway , times(1)).update(customer);
        }


        @Test
        @DisplayName("Should Throw User Not Found Exception")
        public void shouldThrowUserNotFoundException(){
            final Long customerId = customer.getId();

            when(customerGateway.update(customer))
                    .thenThrow(new UserNotFoundException("User not found with id: " + customerId));

            UserNotFoundException exception = assertThrows(UserNotFoundException.class ,
                    () -> updateCustomer.update(customer));

            assertEquals("User not found with id: " + customerId, exception.getMessage());
            verify(validatorCustomer, times(1)).validate(customer);
            verify(customerGateway , times(1)).update(customer);
        }

    }

}