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

    public AddressDTOBuilder withCustomerId(Long customerId){
        this.customerId = customerId;
        return this;
    }

    public AddressDTOBuilder withStreet(String street){
        this.street = street;
        return this;
    }

    public AddressDTOBuilder withCity(String city){
        this.city = city;
        return this;
    }

    public AddressDTOBuilder withState(String state){
        this.state = state;
        return this;
    }

    public AddressDTOBuilder withZip(String zip){
        this.zip = zip;
        return this;
    }

    public AddressDTOBuilder withCountry(String country){
        this.country = country;
        return this;
    }

    public AddressRequestDTO buildAddressRequestDTO(){
        return new AddressRequestDTO(customerId, street, city, state, zip, country);
    }

}
