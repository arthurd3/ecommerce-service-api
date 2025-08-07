package com.arthur.ecommerceapi.customers.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.repositories.AddressRepository;
import com.arthur.ecommerceapi.testFactory.DataTestFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AddressGatewayImplTest {

    @Autowired
    private AddressGatewayImpl addressGateway;

    @Autowired
    private EntityManager entityManager;

    @Nested
    class saveAddress{

        @Test
        public void shouldCreateAddressWithSuccess(){

            Address address = DataTestFactory.createAddress();

            Address savedAddress = addressGateway.save(address);
            entityManager.flush();

            assertNotNull(savedAddress.getId());
            assertEquals(address.getCity(), savedAddress.getCity());
            assertEquals(address.getCountry(), savedAddress.getCountry());
            assertEquals(address.getStreet(), savedAddress.getStreet());
            assertEquals(address.getZip(), savedAddress.getZip());
        }

        @Test
        public void shouldCreateAddressWithFailure(){
            Address address = DataTestFactory.createAddress();
            Customer customer = DataTestFactory.createCustomer();
            customer.setAddress(address);

            addressGateway.save(address);
            entityManager.flush();

            Address address2 = new Address();
            address2.setStreet("123 Main Street");
            address2.setCity("Main City");
            address2.setState("Main State");
            address2.setZip("12345");
            address2.defineCustomer(customer);

            assertThrows(IllegalArgumentException.class, () -> {
                addressGateway.save(address2);
                entityManager.flush();
            });
            
        }

    }

}