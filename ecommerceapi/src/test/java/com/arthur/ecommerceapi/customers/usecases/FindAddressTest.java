package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.gateways.AddressGateway;
import com.arthur.ecommerceapi.testFactory.DataTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class FindAddressTest {

    @InjectMocks
    private FindAddress findAddress;

    @Mock
    private AddressGateway addressGateway;

    @Nested
    class findAddress{

        private Address address;

        @BeforeEach
        void setUp() {
            address = DataTestFactory.createAddress();
        }

        @Test
        @DisplayName("Shoul find address with success")
        public void shouldFindAddressWithSuccess(){
            Long idAddress = address.getId();
            when(addressGateway.findById(address.getId())).thenReturn(address);

            Address addressReturn = findAddress.findById(idAddress);

            assertNotNull(addressReturn);
            assertEquals(address, addressReturn);

            verify(addressGateway , times(1)).findById(idAddress);
        }


    }


}