package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.exceptions.AddressNotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class FindAddressTest {

    @InjectMocks
    private FindAddress findAddress;

    @Mock
    private AddressGateway addressGateway;

    @Nested
    class FindAddressModel{

        private Address address;

        @BeforeEach
        void setUp() {
            address = DataTestFactory.createAddress();
        }

        @Test
        @DisplayName("Should find address with success")
        public void shouldFindAddressWithSuccess(){
            Long idAddress = address.getId();
            when(addressGateway.findById(address.getId())).thenReturn(address);

            Address addressReturn = findAddress.findById(idAddress);

            assertNotNull(addressReturn);
            assertEquals(address, addressReturn);

            verify(addressGateway , times(1)).findById(idAddress);
        }

        @Test
        @DisplayName("Should Throw Address Not Found Exception")
        public void shouldThrowAddressNotFoundException(){
            Long idAddress = address.getId();

            when(addressGateway.findById(address.getId()))
                    .thenThrow(new AddressNotFoundException("Addres with id :" + idAddress + " not found!"));

            AddressNotFoundException exception = assertThrows(AddressNotFoundException.class ,
                    () -> findAddress.findById(idAddress));


            assertEquals(exception.getMessage(), "Addres with id :" + idAddress + " not found!");

            verify(addressGateway , times(1)).findById(idAddress);
        }
    }


}