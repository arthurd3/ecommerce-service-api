package com.arthur.ecommerceapi.customers.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.exceptions.UserNotFoundException;
import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.customers.repositories.CustomerRepository;
import com.arthur.ecommerceapi.orders.gateways.entities.OrderEntity;
import com.arthur.ecommerceapi.orders.repositories.OrderRepository;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;
import com.arthur.ecommerceapi.products.repositories.ProductRepository;
import com.arthur.ecommerceapi.testFactory.DataTestFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

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
    class existsCustomerByEmail{

        @BeforeEach
        void setUp() {
            repository.save(DataTestFactory.createCustomerEntity());
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
    class existsCustomerByPhone{

        private CustomerEntity customerEntity;

        @BeforeEach
        void setUp() {
            customerEntity = repository.save(DataTestFactory.createCustomerEntity());
            entityManager.flush();
        }

        @Test
        @DisplayName("Should exists by Phone")
        void shouldExistsByPhone() {
            String phone = customerEntity.getPhone();

            Boolean existsCustomer = customerGateway.existsByPhone(phone);

            assertTrue(existsCustomer);
        }

        @Test
        @DisplayName("Should Not exists by Phone")
        void shouldNotExistsByPhone() {
            Boolean existsCustomer = customerGateway.existsByPhone("5121231562223");

            assertFalse(existsCustomer);
        }
    }

    @Nested
    class findAllCustomers {

        @Nested
        @DisplayName("should Find All When Customers Exist")
        class shouldFindAllWhenCustomersExist {

            @BeforeEach
            void setUp() {
                repository.saveAll(DataTestFactory.createCustomerEntityList());
                entityManager.flush();
            }

            @Test
            void shouldFindAll() {

                PageRequest pageRequest = PageRequest.of(0, 2);

                Page<Customer> returnedPage = customerGateway.findAll(pageRequest);

                assertNotNull(returnedPage);
                assertEquals(3, returnedPage.getTotalElements());
                assertEquals(2, returnedPage.getTotalPages());
                assertEquals(2, returnedPage.getContent().size());
                assertEquals("Jose", returnedPage.getContent().getFirst().getName());
            }
        }

        @Nested
        @DisplayName("Should Find When No Customers Exist")
        class shouldFindWhenNoCustomersExist {

            @Test
            void shouldFindNoneCustomers() {

                PageRequest pageRequest = PageRequest.of(0, 2);

                Page<Customer> returnedPage = customerGateway.findAll(pageRequest);

                assertNotNull(returnedPage);
                assertEquals(0, returnedPage.getTotalElements());
                assertEquals(0, returnedPage.getNumberOfElements());
                assertEquals(0, returnedPage.getTotalPages());
                assertTrue(returnedPage.getContent().isEmpty());
            }
        }

    }

    @Nested
    class findCustomerById {

        private CustomerEntity savedCustomer;

        @BeforeEach
        void setUp() {
            savedCustomer = repository.save(DataTestFactory.createCustomerEntity());
            entityManager.flush();
        }

        @Test
        @DisplayName("Should Find Customer by Id")
        void shouldFindById() {

            Long findId = savedCustomer.getId();

            Customer returnedCustomer = customerGateway.findById(findId);

            assertNotNull(returnedCustomer);
            assertEquals(findId, returnedCustomer.getId());
            assertEquals("Jose", returnedCustomer.getName());
            assertEquals("3241421421414", returnedCustomer.getPhone());
        }

        @Test
        @DisplayName("Should throw User Not Found Exception on find by id")
        void shouldThrowUserNotFoundException() {
            final Long idFind = 66L;

            UserNotFoundException userNotFoundException = assertThrows(
                    UserNotFoundException.class , () -> customerGateway.findById(idFind));

            assertEquals("User not found with id: " + idFind, userNotFoundException.getMessage());
        }
    }

    @Nested
    class existsCustomerById {

        private CustomerEntity savedCustomer;

        @BeforeEach
        void setUp() {
            savedCustomer = repository.save(DataTestFactory.createCustomerEntity());
            entityManager.flush();
        }

        @Test
        void shouldExistsById() {
            final Long idFind = savedCustomer.getId();

            boolean existsById = customerGateway.existsById(idFind);

            assertTrue(existsById);
        }


        @Test
        void shouldNotExistsById() {
            final Long idFind = 65L;

            boolean existsById = customerGateway.existsById(idFind);

            assertFalse(existsById);
        }

    }

    @Nested
    class deleteCustomerById {

        private CustomerEntity savedCustomer;

        @BeforeEach
        void setUp() {
            CustomerEntity customer = DataTestFactory.createCustomerEntity();
            AddressEntity address = DataTestFactory.createAddressEntity();

            customer.setAddress(address);
            address.setCustomer(customer);

            savedCustomer = repository.save(customer);
            entityManager.flush();
        }

        @Test
        @DisplayName("Should delete Customer by Id")
        void shouldDeleteById() {
            Long idToDelete = savedCustomer.getId();

            customerGateway.delete(idToDelete);

            entityManager.flush();

            assertFalse(customerGateway.existsById(idToDelete));
        }

        @Test
        @DisplayName("Should throw DataIntegrityViolationException on trying to delete a customer with orders")
        void shouldThrowExceptionWhenDeletingCustomerWithOrders() {

            ProductEntity product = DataTestFactory.createProductEntity();
            product = productRepository.save(product);
            entityManager.flush();

            CustomerEntity customerFromDb = repository.findById(savedCustomer.getId()).orElseThrow();
            entityManager.flush();
            
            OrderEntity order = OrderEntity.createObj(product, customerFromDb, customerFromDb.getAddress(), "Sem capa");
            order = orderRepository.save(order);
            entityManager.flush();

            entityManager.clear();

            Exception exception = assertThrows(Exception.class, () -> {
                repository.deleteById(savedCustomer.getId());
                entityManager.flush();
            });
            
            assertTrue(exception instanceof DataIntegrityViolationException ||
                      exception instanceof org.hibernate.exception.ConstraintViolationException,
                      "Expected DataIntegrityViolationException or ConstraintViolationException, but got: " + exception.getClass());
        }
    }

    @Nested
    class updateCustomer{
        private CustomerEntity savedCustomer;

        @BeforeEach
        void setUp() {
            savedCustomer = repository.save(DataTestFactory.createCustomerEntity());
            entityManager.flush();
        }

        @Test
        @DisplayName("Should update customer with success")
        void shouldUpdate() {
            Customer updateCustomer = DataTestFactory.createCustomer();
            updateCustomer.setId(savedCustomer.getId());

            customerGateway.update(updateCustomer);
            entityManager.flush();

            Customer returnedCustomer = customerGateway.findById(savedCustomer.getId());
            assertEquals(updateCustomer.getId(), returnedCustomer.getId());
            assertEquals(updateCustomer.getAddress(), returnedCustomer.getAddress());
            assertEquals(updateCustomer.getName(), returnedCustomer.getName());
        }

        @Test
        @DisplayName("Should dont update customer with success")
        void shouldDontUpdate() {
            Customer updateCustomer = DataTestFactory.createCustomer();
            updateCustomer.setId(66L);

            assertThrows(UserNotFoundException.class ,
                    () -> customerGateway.update(updateCustomer));
        }
    }

    @Nested
    class findCustomerEntityById {

        private CustomerEntity savedCustomer;

        @BeforeEach
        void setUp() {
            savedCustomer = repository.save(DataTestFactory.createCustomerEntity());
            entityManager.flush();
        }

        @Test
        @DisplayName("Should find Customer Entity by Id")
        void shouldFindEntityById() {
            CustomerEntity returnedCustomer = customerGateway.findEntityById(savedCustomer.getId());

            assertNotNull(returnedCustomer);

            assertEquals(savedCustomer.getId(), returnedCustomer.getId());
            assertEquals(savedCustomer.getAddress(), returnedCustomer.getAddress());
            assertEquals(savedCustomer.getName(), returnedCustomer.getName());
        }

        @Test
        @DisplayName("Should Throw User not Found exception on find Customer Entity")
        void shouldThrowUserNotFoundException() {
            assertThrows(UserNotFoundException.class ,
                    () -> customerGateway.findEntityById(3123L));
        }
    }
}