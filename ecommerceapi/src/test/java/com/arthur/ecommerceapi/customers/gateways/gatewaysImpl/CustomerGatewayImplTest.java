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

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = DataTestFactory.createCustomer();
        customer.setId(null);
    }

    @Nested
    class saveCustomer{

        @Test
        @DisplayName("should save customer with success")
        void shouldSaveCustomerWithSuccess() {
            Customer savedCustomer = customerGateway.save(customer);

            assertNotNull(savedCustomer);
            assertNotNull(savedCustomer.getId());
            assertEquals(customer.getName(), savedCustomer.getName());

            Optional<CustomerEntity> entityInDb = repository.findById(savedCustomer.getId());
            assertTrue(entityInDb.isPresent());
            assertEquals(customer.getEmail(), entityInDb.get().getEmail());
        }

        @Test
        @DisplayName("Deve lançar DataIntegrityViolationException ao tentar salvar um email duplicado")
        void shouldThrowExceptionWhenSavingCustomerWithDuplicateEmail() {

            CustomerEntity existingCustomer = new CustomerEntity(null, "Jose Original", "jose@gmail.com", "123", "111", null);
            repository.save(existingCustomer);

            entityManager.flush();
            entityManager.clear();

            Customer customerToFail = DataTestFactory.createCustomer();
            customerToFail.setEmail("jose@gmail.com");
            customerToFail.setId(null);

            assertThrows(DataIntegrityViolationException.class, () -> {
                customerGateway.save(customerToFail);
                entityManager.flush();
            });
        }
    }

    @Test
    void existsByEmail() {
    }

    @Test
    void existsByPhone() {
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