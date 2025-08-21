package com.arthur.ecommerceapi.customers.gateways.entities;

import lombok.Getter;

@Getter
public enum RoleEntityValues {

    ADMIN(1L , "ADMIN"),
    MERCHANT_PREMIUM(2L , "MERCHANT_PREMIUM"),
    MERCHANT(3L , "MERCHANT"),
    CUSTOMER_PREMIUM(4L , "VIP"),
    CUSTOMER(5L ,  "BASIC");

    final Long roleId;
    final String name;

    RoleEntityValues(final Long roleId , final String name) {
        this.roleId = roleId;
        this.name = name;
    }

}