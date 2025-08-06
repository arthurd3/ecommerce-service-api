package com.arthur.ecommerceapi.customers.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.customers.repositories.CustomerRepository;
import com.arthur.ecommerceapi.testFactory.DataTestFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerGatewayImplTest {

    @Autowired
    private CustomerGatewayImpl customerGateway;

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Nested
    class saveCustomer{

        @Test
        @DisplayName("should save customer with success")
        void shouldSaveCustomerWithSuccess() {

            Customer customer = DataTestFactory.createCustomer();
            customer.setId(null);

            Customer savedCustomer = customerGateway.save(customer);

            assertNotNull(savedCustomer);
            assertNotNull(savedCustomer.getId());
            assertEquals(customer.getName(), savedCustomer.getName());

            Optional<CustomerEntity> entityInDb = repository.findById(savedCustomer.getId());
            assertTrue(entityInDb.isPresent());
            assertEquals(customer.getEmail(), entityInDb.get().getEmail());
        }

        @Test
        @DisplayName("Should throw DataIntegrityViolationException on save duplicated e-mail")
        void shouldThrowExceptionWhenSavingCustomerWithDuplicateEmail() {

            CustomerEntity existingCustomer = new CustomerEntity(null, "Jose Original", "jose@gmail.com", "123", "111", null);
            repository.save(existingCustomer);
            entityManager.flush();

            Customer customerToFail = DataTestFactory.createCustomer();
            customerToFail.setEmail("jose@gmail.com");
            customerToFail.setId(null);

            assertThrows(DataIntegrityViolationException.class, () -> {
                customerGateway.save(customerToFail);
                entityManager.flush();
            });
        }
    }

    @Nested
    class existsByEmail{

        @BeforeEach
        void setUp() {
            CustomerEntity existingCustomer = new CustomerEntity(null, "Jose Original", "jose@gmail.com", "123", "111", null);
            repository.save(existingCustomer);
            entityManager.flush();
        }


        @Test
        @DisplayName("Should exists customer by email")
        void shouldExistsByEmail() {
            Boolean existsCustomer = customerGateway.existsByEmail("jose@gmail.com");

            assertTrue(existsCustomer);
        }

        @Test
        @DisplayName("Should not exists customer by email")
        void shouldNotExistsByEmail() {
            Boolean existsCustomer = customerGateway.existsByEmail("jose222@gmail.com");

            assertFalse(existsCustomer);
        }
    }

    @Nested
    class existsByPhone{

        @BeforeEach
        void setUp() {
            CustomerEntity existingCustomer = new CustomerEntity(null, "Jose Original", "jose@gmail.com", "123", "111", null);
            repository.save(existingCustomer);
            entityManager.flush();
        }

        @Test
        @DisplayName("Should exists by Phone")
        void shouldExistsByPhone() {
            Boolean existsCustomer = customerGateway.existsByPhone("111");

            assertTrue(existsCustomer);
        }

        @Test
        @DisplayName("Should Not exists by Phone")
        void shouldNotExistsByPhone() {
            Boolean existsCustomer = customerGateway.existsByPhone("123567");

            assertFalse(existsCustomer);
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void existsById() {
    }

    @Test
    void update() {
    }

    @Test
    void findEntityById() {
    }
}