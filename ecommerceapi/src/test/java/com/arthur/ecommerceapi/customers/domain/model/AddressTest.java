package com.arthur.ecommerceapi.customers.domain.model;

import com.arthur.ecommerceapi.testFactory.DataTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private Address address;

    @BeforeEach
    void setUp() {
         address = DataTestFactory.createAddress();
    }

    @Test
    @DisplayName("Should define customer with success")
    void shouldDefineCustomerWithSuccess() {
        Customer customer = DataTestFactory.createCustomer();
        customer.defineAddress(address);
        address.defineCustomer(customer);

        assertNotNull(address.getCustomer());
        assertEquals(address, customer.getAddress());
        assertEquals(address.getCustomer().getName(), customer.getName());
    }

    @Test
    @DisplayName("Should throw NullPointerException with customer null")
    void shouldThrowNullPointerExceptionWithCustomerNull() {

        assertThrows(NullPointerException.class,
                () -> {address.defineCustomer(null);});

        assertNull(address.getCustomer());
    }
}