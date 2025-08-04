package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
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
    class createAddressWithSuccess{

        @Test
        @DisplayName("Should create Address With Success")
        void shouldCreateAddressWithSuccess(){
             
            final Long customerId = 1L;
            final Address address = DataTestFactory.createAddress();
            Customer customer = DataTestFactory.createCustomer();
            customer.setAddress(null);
            when(findCustomer.findById(customerId)).thenReturn(customer);
            when(customerGateway.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

            Address addressReturn = createAddress.create(address, customerId);

            verify(findCustomer, times(1)).findById(customerId);

            assertNotNull(addressReturn);
            assertEquals(address, addressReturn);
            assertEquals(customer.getAddress(), addressReturn);
            assertEquals(address.getCustomer(), customer);
        }

    }
}