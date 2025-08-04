package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
            
        }

    }
}