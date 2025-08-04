package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.exceptions.UserAlreadyExistsException;
import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
import com.arthur.ecommerceapi.testFactory.DataTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCustomerTest {

    @InjectMocks
    private CreateCustomer createCustomer;

    @Mock
    private CustomerGateway customerGateway;

    @Mock
    private ValidatorCustomer validatorCustomer;

    @Nested
    class createCustomerRequest {

        @Test
        @DisplayName("Should create a customer with success")
        void shouldCreateCustomerWithSuccess(){
            Customer customer = DataTestFactory.createCustomer();

            when(customerGateway.save(any(Customer.class))).thenReturn(customer);

            Customer createdCustomer = createCustomer.create(customer);

            verify(validatorCustomer, times(1)).validate(customer);

            verify(customerGateway, times(1)).save(customer);

            assertNotNull(createdCustomer);
            assertEquals(customer.getName(), createdCustomer.getName());
        }


        @Test
        @DisplayName("Should throw a User Already Exists Exception on create Customer")
        void shouldThrowUserAlreadyExistsExceptionWithEmail(){
            Customer customer = DataTestFactory.createCustomer();

            UserAlreadyExistsException exceptionToThrow = new UserAlreadyExistsException("Email already exists");

            doThrow(exceptionToThrow).when(validatorCustomer).validate(customer);


            UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                    () -> createCustomer.create(customer));


            verify(validatorCustomer, times(1)).validate(customer);

            verify(customerGateway, never()).save(any(Customer.class));

            assertEquals(exceptionToThrow.getMessage(), exception.getMessage());
        }

        @Test
        @DisplayName("Should throw a User Already Exists Exception on create Customer")
        void shouldThrowUserAlreadyExistsExceptionWithPhone(){
            Customer customer = DataTestFactory.createCustomer();

            UserAlreadyExistsException exceptionToThrow = new UserAlreadyExistsException("Phone already exists");

            doThrow(exceptionToThrow).when(validatorCustomer).validate(customer);


            UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
                    () -> createCustomer.create(customer));


            verify(validatorCustomer, times(1)).validate(customer);

            verify(customerGateway, never()).save(any(Customer.class));

            assertEquals(exceptionToThrow.getMessage(), exception.getMessage());
        }

    }
}