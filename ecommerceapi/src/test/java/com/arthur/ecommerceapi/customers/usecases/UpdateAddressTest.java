package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.gateways.AddressGateway;
import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customers.gateways.gatewaysImpl.AddressGatewayImpl;
import com.arthur.ecommerceapi.testFactory.DataTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UpdateAddressTest {

    @InjectMocks
    private UpdateAddress updateAddress;

    @Mock
    private AddressGateway addressGateway;

    @Mock
    private AddressGatewayImpl  addressGatewayImpl;

    @Nested
    class updateAddress{

        private Address addressToUpdate;

        private AddressEntity addressEntity;

        @BeforeEach
        void setUp() {
            addressToUpdate = DataTestFactory.createAddress();
            addressEntity = DataTestFactory.createAddressEntity();
        }

        @Test
        @DisplayName("should update address with success")
        public void shouldUpdateAddressWithSuccess(){

            when(addressGatewayImpl.getAddressEntity(addressToUpdate.getId())).thenReturn(addressEntity);

            Address updatedAddress = updateAddress.update(addressToUpdate);

            assertNotNull(updatedAddress);
            assertEquals(updatedAddress.getId(), addressToUpdate.getId());
        }
    }

}