package com.arthur.ecommerceapi.testFactory;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

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

    @DisplayName("Create a list of 3 customers for Test")
    public static List<Customer> createCustomerList() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Jose");
        customer1.setEmail("jose@gmail.com");
        customer1.setPhone("32988887777");
        customer1.setPassword("12345");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("Maria");
        customer2.setEmail("maria@gmail.com");
        customer2.setPhone("32977776666");
        customer2.setPassword("abcde");

        Customer customer3 = new Customer();
        customer3.setId(3L);
        customer3.setName("Carlos");
        customer3.setEmail("carlos@gmail.com");
        customer3.setPhone("32966665555");
        customer3.setPassword("zyxw");

        return Arrays.asList(customer1, customer2, customer3);
    }
}
