package com.arthur.ecommerceapi.customers.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.customers.repositories.CustomerRepository;
import com.arthur.ecommerceapi.testFactory.builders.AddressTestBuilder;
import com.arthur.ecommerceapi.testFactory.builders.CustomerTestBuilder;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AddressGatewayImplTest {

    @Autowired
    private AddressGatewayImpl addressGateway;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EntityManager entityManager;

    @Nested
    @DisplayName("Should save address (Create)")
    class saveAddress {

        @Test
        @DisplayName("Should create Address with Success")
        void shouldSaveAddressSuccessfully() {

            CustomerEntity existingCustomer = CustomerTestBuilder.aCustomer()
                    .withUniqueEmail()
                    .withUniquePhone()
                    .buildEntity();
            customerRepository.saveAndFlush(existingCustomer);

            Address newAddress = AddressTestBuilder.anAddress()
                    .withStreet("Rua das Flores, 456")
                    .withCity("São Paulo")
                    .withCustomer(CustomerTestBuilder.aCustomer()
                            .withId(existingCustomer.getId())
                            .buildDomain())
                    .buildDomain();

            Address savedAddress = addressGateway.save(newAddress);
            entityManager.flush();

            assertNotNull(savedAddress);
            assertNotNull(savedAddress.getId());
            assertNotNull(savedAddress.getCustomer());
            assertEquals(existingCustomer.getId(), savedAddress.getCustomer().getId());
            assertEquals("Rua das Flores, 456", savedAddress.getStreet());
            assertEquals("São Paulo", savedAddress.getCity());
        }

        @Test
        @DisplayName("Should throw DataIntegrityViolationException when customer does not exist")
        void shouldThrowExceptionWhenSavingAddressWithNonExistentCustomer() {

            final Long NON_EXISTENT_CUSTOMER_ID = 999L;
            
            Address addressToFail = AddressTestBuilder.anAddress()
                    .withCustomer(CustomerTestBuilder.aCustomer()
                            .withId(NON_EXISTENT_CUSTOMER_ID)
                            .buildDomain())
                    .buildDomain();

            DataIntegrityViolationException exception = assertThrows(
                    DataIntegrityViolationException.class, 
                    () -> {
                        addressGateway.save(addressToFail);
                        entityManager.flush();
                    }
            );
            
            assertNotNull(exception.getMessage());
        }

        @Test
        @DisplayName("Should throw DataIntegrityViolationException when saving second address for same customer")
        void shouldThrowExceptionWhenSavingSecondAddressForSameCustomer() {

            CustomerEntity customer = CustomerTestBuilder.aCustomer()
                    .withUniqueEmail()
                    .withUniquePhone()
                    .buildEntity();
            
            AddressEntity address1 = AddressTestBuilder.anAddress()
                    .withStreet("Primeiro Endereço, 123")
                    .withCustomerEntity(customer)
                    .buildEntity();
            
            customer.setAddress(address1);
            address1.setCustomer(customer);
            
            customerRepository.saveAndFlush(customer);
            entityManager.flush();
            entityManager.clear();

            Address secondAddress = AddressTestBuilder.anAddress()
                    .withStreet("Segundo Endereço, 456")
                    .withCustomer(CustomerTestBuilder.aCustomer()
                            .withId(customer.getId())
                            .buildDomain())
                    .buildDomain();

            DataIntegrityViolationException exception = assertThrows(
                    DataIntegrityViolationException.class, 
                    () -> {
                        addressGateway.save(secondAddress);
                        entityManager.flush();
                    }
            );
            
            assertNotNull(exception.getMessage());
            assertTrue(exception.getMessage().contains("Unique") || 
                      exception.getMessage().contains("CONSTRAINT"), 
                      "Exception should be related to unique constraint violation");
        }

    }
}