package com.arthur.ecommerceapi.testFactory.builders;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;

public class CustomerTestBuilder {
    
    private String name = "Default Customer";
    private String email = "default@test.com";
    private String phone = "11999999999";
    private String password = "defaultPass123";
    private Long id;

    public static CustomerTestBuilder aCustomer() {
        return new CustomerTestBuilder();
    }

    public CustomerTestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerTestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerTestBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerTestBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerTestBuilder withUniqueEmail() {
        this.email = "unique_" + System.currentTimeMillis() + "@test.com";
        return this;
    }

    public CustomerTestBuilder withUniquePhone() {
        this.phone = "11" + System.currentTimeMillis() % 1000000000L;
        return this;
    }

    public Customer buildDomain() {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setPassword(password);
        return customer;
    }

    public CustomerEntity buildEntity() {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setEmail(email);
        entity.setPhone(phone);
        entity.setPassword(password);
        return entity;
    }
}
