package com.arthur.ecommerceapi.customers.domain.model;

import lombok.Getter;

@Getter
public enum RoleValues {

    ADMIN(1L , "ADMIN"),
    MERCHANT_PREMIUM(2L , "MERCHANT_PREMIUM"),
    MERCHANT(3L , "MERCHANT"),
    CUSTOMER_PREMIUM(4L , "VIP"),
    CUSTOMER(5L ,  "BASIC");

    final Long roleId;
    final String name;

    RoleValues(final Long roleId , final String name) {
        this.roleId = roleId;
        this.name = name;
    }

}