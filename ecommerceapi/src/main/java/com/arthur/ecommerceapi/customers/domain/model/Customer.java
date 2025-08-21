package com.arthur.ecommerceapi.customers.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Customer {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Address address;
    private Set<Role> roles = new HashSet<>();;

    public void defineAddress(Address address) {
        if(address == null)
            throw new NullPointerException("Address is null");
        this.address = address;

        if (address.getCustomer() != this) {
            address.defineCustomer(this);
        }
    }

    public void addRole(final Role role){
        if(role == null)
            throw new NullPointerException("Role is null");

        this.getRoles().add(role);
    }

}