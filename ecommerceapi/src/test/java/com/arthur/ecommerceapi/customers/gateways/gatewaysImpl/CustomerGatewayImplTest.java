package com.arthur.ecommerceapi.customers.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.gateways.mappers.GatewayMapper;
import com.arthur.ecommerceapi.customers.repositories.CustomerRepository;
import com.arthur.ecommerceapi.testFactory.DataTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerGatewayImplTest {

    @Autowired
    private CustomerGatewayImpl customerGateway;

    @Autowired
    private CustomerRepository repository;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = DataTestFactory.createCustomer();
        customer.setId(null);
    }

    @Test
    void save() {
        Customer savedCustomer = customerGateway.save(customer);

        assertNotNull(savedCustomer);
        assertNotNull(savedCustomer.getId());
        assertEquals(customerToSave.getName(), savedCustomer.getName());

        Optional<CustomerEntity> entityInDb = repository.findById(savedCustomer.getId());
        assertTrue(entityInDb.isPresent());
        assertEquals(customerToSave.getEmail(), entityInDb.get().getEmail());
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