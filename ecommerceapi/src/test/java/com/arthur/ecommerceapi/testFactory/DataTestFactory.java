package com.arthur.ecommerceapi.testFactory;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@DisplayName("Create Objects for Test")
public class DataTestFactory {

    @DisplayName("Create a customer for Test")
    public static Customer createCustomer(){
        Customer customer = new Customer();
        customer.setEmail("antonio@gmail.com");
        customer.setName("Antonio");
        customer.setPassword("1234567890");
        customer.setPhone("512341545123");
        return customer;
    }

    @DisplayName("Create a customer for Test")
    public static CustomerEntity createCustomerEntity(){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setEmail("jose@gmail.com");
        customerEntity.setName("Jose");
        customerEntity.setPassword("12345");
        customerEntity.setPhone("3241421421414");
        return customerEntity;
    }

    @DisplayName("Create a Address for Test")
    public static Address createAddress(){
        Address address = new Address();
        address.setStreet("123 Main Street");
        address.setCity("Main City");
        address.setState("Main State");
        address.setZip("12345");
        return address;
    }

    @DisplayName("Create a Address Entity for Test")
    public static AddressEntity createAddressEntity(){
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet("Nt Street");
        addressEntity.setCity("New York");
        addressEntity.setState("Paris");
        addressEntity.setZip("1333");
        addressEntity.setCountry("USA");
        return addressEntity;
    }


    @DisplayName("Create a Product Entity for Test")
    public static ProductEntity createProductEntity(){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("HotWellsCar");
        productEntity.setCategory(ProductCategory.TOYS_AND_GAMES);
        productEntity.setPrice(new BigDecimal(200));
        productEntity.setDescription("Red car");
        productEntity.setQuantity(2);
        productEntity.setAvailableToDiscount(false);
        return productEntity;
    }

    @DisplayName("Create a Product for Test")
    public static Product createProduct(){
        Product product = new Product();
        product.setName("HotWellsCar");
        product.setCategory(ProductCategory.TOYS_AND_GAMES);
        product.setPrice(new Money("200"));
        product.setDescription("Red car");
        product.setQuantity(2);
        product.setAvailableToDiscount(false);
        return product;
    }

    @DisplayName("Create a list of 3 customers for Test")
    public static List<Customer> createCustomerList() {
        Customer customer1 = new Customer();
        customer1.setName("Jose");
        customer1.setEmail("jose@gmail.com");
        customer1.setPhone("32988887777");
        customer1.setPassword("12345");

        Customer customer2 = new Customer();
        customer2.setName("Maria");
        customer2.setEmail("maria@gmail.com");
        customer2.setPhone("32977776666");
        customer2.setPassword("abcde");

        Customer customer3 = new Customer();
        customer3.setName("Carlos");
        customer3.setEmail("carlos@gmail.com");
        customer3.setPhone("32966665555");
        customer3.setPassword("zyxw");

        return Arrays.asList(customer1, customer2, customer3);
    }


    @DisplayName("Create a list of 3 customers entities for Test")
    public static List<CustomerEntity> createCustomerEntityList() {
        CustomerEntity customer1 = new CustomerEntity();
        customer1.setName("Jose");
        customer1.setEmail("jose@gmail.com");
        customer1.setPhone("32988887777");
        customer1.setPassword("12345");

        CustomerEntity customer2 = new CustomerEntity();
        customer2.setName("Maria");
        customer2.setEmail("maria@gmail.com");
        customer2.setPhone("32977776666");
        customer2.setPassword("abcde");

        CustomerEntity customer3 = new CustomerEntity();
        customer3.setName("Carlos");
        customer3.setEmail("carlos@gmail.com");
        customer3.setPhone("32966665555");
        customer3.setPassword("zyxw");

        return Arrays.asList(customer1, customer2, customer3);
    }
}
