package com.arthur.ecommerceapi.testFactory.builders;

import com.arthur.ecommerceapi.customers.dtos.request.AddressRequestDTO;

public class AddressDTOBuilder {

    private Long customerId;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;

    public static AddressDTOBuilder aAddressReqDTO(){
        return new AddressDTOBuilder();
    }

    public void withCustomerId(Long customerId){
        this.customerId = customerId;
    }

    public void withStreet(String street){
        this.street = street;
    }

    public void withCity(String city){
        this.city = city;
    }

    public void withState(String state){
        this.state = state;
    }

    public void withZip(String zip){
        this.zip = zip;
    }

    public void withCountry(String country){
        this.country = country;
    }

    public AddressRequestDTO buildAddressRequestDTO(){
        return new AddressRequestDTO(customerId, street, city, state, zip, country);
    }
}
