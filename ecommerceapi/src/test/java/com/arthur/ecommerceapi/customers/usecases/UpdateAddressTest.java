package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.exceptions.AddressNotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UpdateAddressTest {

    @InjectMocks
    private UpdateAddress updateAddress;

    @Mock
    private AddressGateway addressGateway;

    @Nested
    class updateAddress{

        private Address addressToUpdate;

        @BeforeEach
        void setUp() {
            addressToUpdate = DataTestFactory.createAddress();
        }

        @Test
        @DisplayName("Should update address with success")
        public void shouldUpdateAddressWithSuccess(){
            when(addressGateway.update(any(Address.class))).thenAnswer(invocation -> invocation.getArgument(0));

            Address updatedAddress = updateAddress.update(addressToUpdate);

            assertNotNull(updatedAddress);
            assertEquals(updatedAddress.getId(), addressToUpdate.getId());
            assertEquals(updatedAddress.getStreet(), addressToUpdate.getStreet());
            assertEquals(updatedAddress.getCity(), addressToUpdate.getCity());

            verify(addressGateway, times(1)).update(any(Address.class));
        }

        @Test
        @DisplayName("Should Throw Address Not Found Exception")
        public void shouldThrowAddressNotFoundException(){
            final Long addressId = addressToUpdate.getId();

            when(addressGateway.update(addressToUpdate))
                    .thenThrow(new AddressNotFoundException("Address with id :" + addressId + " not found!"));

            AddressNotFoundException exception = assertThrows(AddressNotFoundException.class,
                    () -> updateAddress.update(addressToUpdate));

            assertEquals("Address with id :" + addressId + " not found!", exception.getMessage());
            verify(addressGateway, times(1)).update(addressToUpdate);
        }
    }

}