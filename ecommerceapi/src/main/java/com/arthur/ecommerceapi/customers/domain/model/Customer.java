package com.arthur.ecommerceapi.customers.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Address address;

    public void defineAddress(Address address) {
        if(address == null)
            throw new NullPointerException("Address is null");
        this.address = address;

        if (address.getCustomer() != this) {
            address.defineCustomer(this);
        }
    }
}