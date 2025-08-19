package com.arthur.ecommerceapi.customers.gateways.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CustomMetadata {

    private List<String> role;

    private List<String> permission;

    public CustomMetadata() {
        this.role = List.of("ROLE_ADMIN", "ROLE_MERCHANT_PREMIUM" , "ROLE_MERCHANT" , "ROLE_CUSTOMER");
        this.permission = List.of("EDIT", "DELETE", "CREATE" , "BUY" , "SALE" , "CREATE_PRODUCT");
    }
}