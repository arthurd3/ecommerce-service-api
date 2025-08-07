package com.arthur.ecommerceapi.customers.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.exceptions.UserAlreadyHaveAddressException;
import com.arthur.ecommerceapi.customers.gateways.CustomerGateway;
import com.arthur.ecommerceapi.testFactory.builders.AddressTestBuilder;
import com.arthur.ecommerceapi.testFactory.builders.CustomerTestBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CreateAddress Use Case Tests")
class CreateAddressImprovedTest {

    @InjectMocks
    private CreateAddress createAddress;

    @Mock
    private FindCustomer findCustomer;

    @Mock 
    private CustomerGateway customerGateway;

    @Nested
    @DisplayName("When creating address for customer")
    class WhenCreatingAddressForCustomer {

        private static final Long CUSTOMER_ID = 1L;

        @Test
        @DisplayName("Should create address successfully when customer has no address")
        void shouldCreateAddressSuccessfully() {
            Customer customerWithoutAddress = CustomerTestBuilder.aCustomer()
                    .withId(CUSTOMER_ID)
                    .withName("João Silva")
                    .buildDomain();
            customerWithoutAddress.setAddress(null);

            Address addressToCreate = AddressTestBuilder.anAddress()
                    .withStreet("Rua Nova, 123")
                    .withCity("Rio de Janeiro")
                    .buildDomain();

            when(findCustomer.findById(CUSTOMER_ID)).thenReturn(customerWithoutAddress);
            when(customerGateway.save(any(Customer.class)))
                    .thenAnswer(invocation -> invocation.getArgument(0));

            Address result = createAddress.create(addressToCreate, CUSTOMER_ID);

            assertNotNull(result);
            assertEquals(addressToCreate.getStreet(), result.getStreet());
            assertEquals(addressToCreate.getCity(), result.getCity());
            assertEquals(customerWithoutAddress, result.getCustomer());
            assertEquals(result, customerWithoutAddress.getAddress());
            
            verify(findCustomer).findById(CUSTOMER_ID);
            verify(customerGateway, never()).save(any(Customer.class)); // Como está comentado no código
        }

        @Test
        @DisplayName("Should throw UserAlreadyHaveAddressException when customer already has address")
        void shouldThrowExceptionWhenCustomerAlreadyHasAddress() {
            Customer customerWithAddress = CustomerTestBuilder.aCustomer()
                    .withId(CUSTOMER_ID)
                    .buildDomain();
            
            Address existingAddress = AddressTestBuilder.anAddress()
                    .withStreet("Endereço Existente, 456")
                    .buildDomain();
            customerWithAddress.setAddress(existingAddress);

            Address newAddressToCreate = AddressTestBuilder.anAddress()
                    .withStreet("Novo Endereço, 789")
                    .buildDomain();

            when(findCustomer.findById(CUSTOMER_ID)).thenReturn(customerWithAddress);

            UserAlreadyHaveAddressException exception = assertThrows(
                    UserAlreadyHaveAddressException.class,
                    () -> createAddress.create(newAddressToCreate, CUSTOMER_ID)
            );

            assertEquals("This User already has an address", exception.getMessage());
            
            verify(findCustomer).findById(CUSTOMER_ID);
            verify(customerGateway, never()).save(any(Customer.class));
        }

        @Test
        @DisplayName("Should handle null address gracefully")
        void shouldHandleNullAddressGracefully() {
            Customer customer = CustomerTestBuilder.aCustomer()
                    .withId(CUSTOMER_ID)
                    .buildDomain();
            customer.setAddress(null);

            when(findCustomer.findById(CUSTOMER_ID)).thenReturn(customer);

            assertThrows(
                    NullPointerException.class,
                    () -> createAddress.create(null, CUSTOMER_ID)
            );

            verify(findCustomer).findById(CUSTOMER_ID);
        }
    }
}
