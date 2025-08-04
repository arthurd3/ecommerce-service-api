package com.arthur.ecommerceapi.testFactory;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Create Objects for Test")
public class DataTestFactory {

    @DisplayName("Create a customer for Test")
    public static Customer createCustomer(){
        Customer findedCustomer = new Customer();
        findedCustomer.setId(1L);
        findedCustomer.setEmail("jose@gmail.com");
        findedCustomer.setName("Jose");
        findedCustomer.setPassword("12345");
        findedCustomer.setPhone("3241421421414");
        return findedCustomer;
    }

    @DisplayName("Create a Address for Test")
    public static Address createAddress(){
        Address findedAddress = new Address();
        findedAddress.setId(1L);
        findedAddress.setStreet("123 Main Street");
        findedAddress.setCity("Main City");
        findedAddress.setState("Main State");
        findedAddress.setZip("12345");
        return findedAddress;
    }


}
