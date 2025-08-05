package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.gateways.AddressGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

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
            addressToUpdate = new Address();
        }

        public void shouldUpdateAddressWithSuccess(){

        }
    }

}