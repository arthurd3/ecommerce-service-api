package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.exceptions.EmailAlreadyExistsException;
import com.arthur.ecommerceapi.customers.exceptions.PhoneAlreadyExistsException;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ValidatorCustomerTest {

    @InjectMocks
    private ValidatorCustomer validator;

    @Mock
    private CustomerGateway customerGateway;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = DataTestFactory.createCustomer();
    }

    @Nested
    class ValidateCustomerModel{

        @Test
        @DisplayName("Should validate customer with success")
        void shouldValidateCustomer(){

            when(customerGateway.existsByEmail(customer.getEmail())).thenReturn(false);
            when(customerGateway.existsByPhone(customer.getPhone())).thenReturn(false);

            assertDoesNotThrow(() -> validator.validate(customer));

            verify(customerGateway , times(1)).existsByEmail(customer.getEmail());
            verify(customerGateway , times(1)).existsByPhone(customer.getPhone());
        }

        @Test
        @DisplayName("Should Throw Email Already Exists Exception")
        void shouldThrowEmailAlreadyExistsException(){
            when(customerGateway.existsByEmail(customer.getEmail())).thenReturn(true);

            EmailAlreadyExistsException exception = assertThrows(
                    EmailAlreadyExistsException.class , () -> validator.validate(customer));

            assertEquals("Email already exists", exception.getMessage());
            verify(customerGateway , times(1)).existsByEmail(customer.getEmail());
            verify(customerGateway, never()).existsByPhone(anyString());
        }

        @Test
        @DisplayName("Should Throw Phone Already Exists Exception")
        void shouldThrowPhoneAlreadyExistsException(){
            when(customerGateway.existsByEmail(customer.getEmail())).thenReturn(false);
            when(customerGateway.existsByPhone(customer.getPhone())).thenReturn(true);

            PhoneAlreadyExistsException exception = assertThrows(
                    PhoneAlreadyExistsException.class , () -> validator.validate(customer));

            assertEquals("Phone already exists", exception.getMessage());
            verify(customerGateway , times(1)).existsByEmail(customer.getEmail());
            verify(customerGateway , times(1)).existsByPhone(customer.getPhone());
        }

    }

    @Nested
    class ValidateExistsCustomer{
        @Test
        @DisplayName("Should exist User")
        void shouldValidateExists(){
            when(customerGateway.existsById(customer.getId())).thenReturn(true);

            assertDoesNotThrow(() -> validator.validateExists(customer.getId()));

            verify(customerGateway , times(1)).existsById(customer.getId());
        }


        @Test
        @DisplayName("Should exist User Not Found Exception")
        void shouldThrowUserNotFoundException(){

            when(customerGateway.existsById(customer.getId())).thenReturn(false);

            UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                    () -> validator.validateExists(customer.getId()));

            assertEquals("Customer not exists", exception.getMessage());
            verify(customerGateway , times(1)).existsById(customer.getId());
        }
    }

}