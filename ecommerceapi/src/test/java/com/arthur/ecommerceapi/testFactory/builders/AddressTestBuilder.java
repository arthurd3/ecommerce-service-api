package com.arthur.ecommerceapi.testFactory.builders;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.dtos.request.AddressPutRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.request.AddressRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.response.AddressResponseDTO;
import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.orders.dtos.response.AddressOrderResponseDTO;

public class AddressTestBuilder {

    private Long id;
    private String street = "Default Street, 123";
    private String city = "Default City";
    private String state = "Default State";
    private String zip = "12345-678";
    private String country = "Brazil";
    private Customer customer;
    private CustomerEntity customerEntity;

    public static AddressTestBuilder anAddress() {
        return new AddressTestBuilder();
    }

    public AddressTestBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressTestBuilder withZip(String zip) {
        this.zip = zip;
        return this;
    }

    public AddressTestBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public AddressTestBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AddressTestBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressTestBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public AddressTestBuilder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public AddressTestBuilder withCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
        return this;
    }


    public Address buildDomain() {
        Address address = new Address();
        address.setId(id);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZip(zip);
        address.setCountry(country);
        
        if (customer != null) {
            address.defineCustomer(customer);
        }
        
        return address;
    }

    public AddressEntity buildEntity() {
        AddressEntity entity = new AddressEntity();
        entity.setId(id);
        entity.setStreet(street);
        entity.setCity(city);
        entity.setState(state);
        entity.setZip(zip);
        entity.setCountry(country);
        entity.setCustomer(customerEntity);
        return entity;
    }

    public AddressRequestDTO buildAddressRequestDTO(){
        return new AddressRequestDTO( street, city, state, zip, country);
    }

    public AddressResponseDTO buildAddressResponseDTO(){
        return new AddressResponseDTO(id, street, city, state, zip, country);
    }

    public AddressPutRequestDTO buildAddressPutRequestDTO(){
        return new AddressPutRequestDTO(street, city, state, zip, country);
    }
}
