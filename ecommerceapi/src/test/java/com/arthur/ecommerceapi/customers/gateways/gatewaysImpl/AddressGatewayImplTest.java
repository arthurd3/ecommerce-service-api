package com.arthur.ecommerceapi.customers.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.exceptions.AddressNotFoundException;
import com.arthur.ecommerceapi.customers.gateways.AddressGateway;
import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.customers.repositories.CustomerRepository;
import com.arthur.ecommerceapi.testFactory.builders.AddressTestBuilder;
import com.arthur.ecommerceapi.testFactory.builders.CustomerTestBuilder;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
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
    private AddressGateway addressGateway;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EntityManager entityManager;

    @Nested
    @DisplayName("Should save address (Create)")
    class SaveAddress {

        @Test
        @DisplayName("Should create Address with Success")
        void shouldSaveAddressSuccessfully() {

            CustomerEntity existingCustomer = CustomerTestBuilder.aCustomer()
                    .withUniqueEmail()
                    .withUniquePhone()
                    .buildEntity();
            customerRepository.saveAndFlush(existingCustomer);

            Address newAddress = AddressTestBuilder.aAddress()
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
            
            Address addressToFail = AddressTestBuilder.aAddress()
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
            
            AddressEntity address1 = AddressTestBuilder.aAddress()
                    .withStreet("Primeiro Endereço, 123")
                    .withCustomerEntity(customer)
                    .buildEntity();
            
            customer.setAddress(address1);
            address1.setCustomer(customer);
            
            customerRepository.saveAndFlush(customer);
            entityManager.flush();
            entityManager.clear();

            Address secondAddress = AddressTestBuilder.aAddress()
                    .withStreet("Segundo Endereço, 456")
                    .withCustomer(CustomerTestBuilder.aCustomer()
                            .withId(customer.getId())
                            .buildDomain())
                    .buildDomain();

            DataIntegrityViolationException exception = assertThrows( DataIntegrityViolationException.class,
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

    @Nested
    @DisplayName("Should find address by id")
    class FindAddressById {

        private AddressEntity savedAddress;
        private Long addressId;

        @BeforeEach
        void setUp() {
            CustomerEntity customer = CustomerTestBuilder.aCustomer().withUniqueEmail().buildEntity();
            AddressEntity address = AddressTestBuilder.aAddress().buildEntity();
            customer.setAddress(address);

            customer = customerRepository.saveAndFlush(customer);
            savedAddress = customer.getAddress();
            entityManager.clear();

            addressId = address.getId();
        }

        @Test
        @DisplayName("Should verify exists Address By Id")
        void shouldVerifyExistsAddressById() {
            Address foundAddress = addressGateway.findById(addressId);

            assertNotNull(foundAddress);
            assertEquals(addressId, foundAddress.getId());
            assertEquals(savedAddress.getCity(), foundAddress.getCity());
        }

        @Test
        @DisplayName("Should throw execpiton on non exist address by id")
        void shouldThrowExceptionWithNonExistentAddressById() {
            final Long NON_EXISTENT_ID = 999L;

            assertThrows(AddressNotFoundException.class,
                    () -> addressGateway.findById(NON_EXISTENT_ID));
        }


    }

    @Nested
    @DisplayName("Should update address by id")
    class UpdateAddressById {

        private Address originalAddress;

        @BeforeEach
        void setUp() {
            CustomerEntity originalCustomer = CustomerTestBuilder.aCustomer().withUniqueEmail().buildEntity();
            AddressEntity address = AddressTestBuilder.aAddress().buildEntity();

            originalCustomer.setAddress(address);
            address.setCustomer(originalCustomer);

            originalCustomer = customerRepository.saveAndFlush(originalCustomer);

            entityManager.clear();

            originalAddress = addressGateway.findById(originalCustomer.getAddress().getId());
        }

        @Test
        @DisplayName("Should update address and dont change Customer with success")
        void shouldUpdateAddressAndDontChangeCustomerWithSuccess() {

            Address addressWithUpdates = AddressTestBuilder.aAddress()
                    .withId(originalAddress.getId())
                    .withStreet("Nova Rua 456")
                    .withCity("Nova Cidade")
                    .withCustomer(null)
                    .buildDomain();

            Address updatedAddress = addressGateway.update(addressWithUpdates);

            assertNotNull(updatedAddress);
            assertEquals("Nova Rua 456", updatedAddress.getStreet());
            assertEquals("Nova Cidade", updatedAddress.getCity());
            assertEquals(originalAddress.getZip(), updatedAddress.getZip());

            assertNotNull(updatedAddress.getCustomer());
            assertEquals(originalAddress.getCustomer().getId(), updatedAddress.getCustomer().getId());
            assertEquals(originalAddress.getCustomer().getName(), updatedAddress.getCustomer().getName());
            assertEquals(originalAddress.getCustomer().getPhone(), updatedAddress.getCustomer().getPhone());
            assertEquals(originalAddress.getCustomer().getEmail(), updatedAddress.getCustomer().getEmail());
        }

        @Test
        @DisplayName("Should Throw Exception on update non exists address")
        void shouldThrowExceptionOnUpdateNonExistentAddress() {
            final Long NON_EXISTENT_ID = 999L;
            Address addressWithUpdates = AddressTestBuilder.aAddress()
                    .withStreet("Nova Rua 456")
                    .withCity("Nova Cidade")
                    .withCustomer(null)
                    .withId(NON_EXISTENT_ID)
                    .buildDomain();

            assertThrows(AddressNotFoundException.class,
                    () -> addressGateway.update(addressWithUpdates));
        }

    }

    @Nested
    @DisplayName("Should get Address Entity with success")
    class FindAddressEntityWithSuccess {
        private AddressEntity originalAddress;

        @BeforeEach
        void setUp() {
            CustomerEntity originalCustomer = CustomerTestBuilder.aCustomer().withUniqueEmail().buildEntity();
            originalAddress = AddressTestBuilder.aAddress().buildEntity();
            originalCustomer.setAddress(originalAddress);
            originalAddress.setCustomer(originalCustomer);

            customerRepository.saveAndFlush(originalCustomer);
            entityManager.clear();
        }

        @Test
        @DisplayName("Should find address entity with success")
        void shouldFindAddressEntityWithSuccess() {
            AddressEntity address = addressGateway.findAddressEntity(originalAddress.getId());

            assertNotNull(address);

            assertEquals(originalAddress.getId(), address.getId());
            assertEquals(originalAddress.getStreet(), address.getStreet());
            assertEquals(originalAddress.getCity(), address.getCity());
            assertEquals(originalAddress.getZip(), address.getZip());
        }

        @Test
        @DisplayName("Should find address entity with success")
        void shouldThrowAddressNotFoundException() {
            final Long NON_EXISTENT_ID = 999L;

            assertThrows(AddressNotFoundException.class ,
                    () -> addressGateway.findAddressEntity(NON_EXISTENT_ID));
        }

    }
    
}