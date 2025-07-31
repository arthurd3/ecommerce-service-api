package com.arthur.ecommerceapi.customer.domain.model;

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
        if (address == null) {
            if (this.address != null) {
                this.address.setCustomer(null);
            }
        } else {
            address.setCustomer(this);
        }
        this.address = address;
    }
}