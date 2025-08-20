package com.arthur.ecommerceapi.customers.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

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
    private Set<Role> roles;

    public void defineCustomer(final Customer customer){
        if(customer == null)
            throw new NullPointerException("Customer is null");

        this.customer = customer;
    }

    public void defineRoles(final Role.RoleValues roles){

    }
}
