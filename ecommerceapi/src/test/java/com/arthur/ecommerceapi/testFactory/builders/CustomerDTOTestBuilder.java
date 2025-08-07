package com.arthur.ecommerceapi.testFactory.builders;

import com.arthur.ecommerceapi.customers.dtos.request.CustomerRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.request.CustomerPutRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.response.CustomerResponseDTO;

public class CustomerDTOTestBuilder {
    
    private String name = "Default Customer";
    private String email = "default@test.com";
    private String phone = "11999999999";
    private String password = "defaultPass123";
    private Long id = 1L;

    public static CustomerDTOTestBuilder aCustomerDTO() {
        return new CustomerDTOTestBuilder();
    }

    public CustomerDTOTestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerDTOTestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerDTOTestBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerDTOTestBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomerDTOTestBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerDTOTestBuilder withUniqueEmail() {
        this.email = "unique_" + System.currentTimeMillis() + "@test.com";
        return this;
    }

    public CustomerRequestDTO buildRequestDTO() {
        return new CustomerRequestDTO(name, email, phone, password);
    }

    public CustomerPutRequestDTO buildPutRequestDTO() {
        return new CustomerPutRequestDTO(name, email, phone);
    }

    public CustomerResponseDTO buildResponseDTO() {
        return new CustomerResponseDTO(id, name, email);
    }
}
