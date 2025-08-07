package com.arthur.ecommerceapi.customers.domain.model;

import com.arthur.ecommerceapi.testFactory.DataTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = DataTestFactory.createCustomer();
    }

    @Test
    @DisplayName("Should define address with success")
    public void shouldDefineAddressWithSuccess() {
        Address address = DataTestFactory.createAddress();
        customer.defineAddress(address);
        address.defineCustomer(customer);

        assertEquals(address, customer.getAddress());
    }


    @Test
    @DisplayName("Should Throw NullPointerException on define null address")
    public void shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> customer.defineAddress(null));

        assertNull(customer.getAddress());
    }


}