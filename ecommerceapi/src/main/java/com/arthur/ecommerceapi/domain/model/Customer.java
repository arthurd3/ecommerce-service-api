package com.arthur.ecommerceapi.domain.model;

import com.arthur.ecommerceapi.exceptions.UserAlreadyHaveAddress;
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
            throw new UserAlreadyHaveAddress("Is not possible define this address");

        this.address = address;
    }
}