package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.exceptions.UserAlreadyHaveAddressException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAddressTest {

    @InjectMocks
    private CreateAddress createAddress;

    @Mock
    private FindCustomer findCustomer;

    @Mock
    private CustomerGateway customerGateway;

    @Nested
    class CreateAddressModel{

        private Address address;
        private final Long customerId = 1L;
        private Customer customer;

        @BeforeEach
        void setUp() {
            customer = DataTestFactory.createCustomer();
            address = DataTestFactory.createAddress();
        }

        @Test
        @DisplayName("Should create Address With Success")
        void shouldCreateAddressWithSuccess(){

            customer.setAddress(null);
            when(findCustomer.findById(customerId)).thenReturn(customer);
            when(customerGateway.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

            Address addressReturn = createAddress.create(address);

            verify(findCustomer, times(1)).findById(customerId);

            assertNotNull(addressReturn);
            assertEquals(address, addressReturn);
            assertEquals(customer.getAddress(), addressReturn);
            assertEquals(address.getCustomer(), customer);
        }

        @Test
        @DisplayName("Should throw User Already Have Address Exception")
        void shouldThrowUserAlreadyHaveAddressException(){

            customer.setAddress(new Address());
            when(findCustomer.findById(customerId)).thenReturn(customer);

            UserAlreadyHaveAddressException exception = assertThrows(UserAlreadyHaveAddressException.class,
                    () -> createAddress.create(address));

            assertEquals("This User already has an address", exception.getMessage());

            verify(customerGateway, never()).save(any(Customer.class));
        }
    }
}