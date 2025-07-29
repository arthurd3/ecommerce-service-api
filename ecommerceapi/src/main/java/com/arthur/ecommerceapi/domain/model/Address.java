package com.arthur.ecommerceapi.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private Customer customer;
}
