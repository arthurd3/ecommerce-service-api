package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.exceptions.UserAlreadyExistsException;
import com.arthur.ecommerceapi.customers.exceptions.UserNotFoundException;
import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
import com.arthur.ecommerceapi.testFactory.DataTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DeleteCustomerTest {

    @InjectMocks
    private DeleteCustomer deleteCustomer;

    @Mock
    private CustomerGateway customerGateway;

    @Mock
    private ValidatorCustomer validatorCustomer;

    @Nested
    class DeleteCustomerModel {

        private Customer customer;

        @BeforeEach
        void setUp() {
            customer = DataTestFactory.createCustomer();
        }

        @Test
        @DisplayName("Should delete customer with success")
        public void shouldDeleteCustomerWithSuccess(){

            deleteCustomer.delete(customer.getId());

            verify(validatorCustomer, times(1)).validateExists(customer.getId());
            verify(customerGateway , times(1)).delete(customer.getId());
        }

        @Test
        @DisplayName("Should throw Customer Not Found Exception on delete")
        public void shouldThrowCustomerNotFoundException(){
            UserNotFoundException exceptionThrow = new UserNotFoundException("Customer not found");
            doThrow(exceptionThrow).when(validatorCustomer).validateExists(customer.getId());

            UserNotFoundException exception = assertThrows(UserNotFoundException.class ,
                    () -> deleteCustomer.delete(customer.getId()));

            assertEquals(exceptionThrow.getMessage(), exception.getMessage());
            verify(validatorCustomer, times(1)).validateExists(customer.getId());
            verify(customerGateway , never()).delete(customer.getId());
        }
    }

}