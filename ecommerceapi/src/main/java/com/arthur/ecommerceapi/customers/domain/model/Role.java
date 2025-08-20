package com.arthur.ecommerceapi.customers.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.security.Permission;
import java.util.Set;

@Getter
@Setter
public class Role {

    private Long roleId;
    private String name;
    private Set<Permission> permission;

    @Getter
    public enum RoleValues {

        ADMIN(1L),
        MERCHANT_PREMIUM(2L),
        MERCHANT(3L),
        CUSTOMER_PREMIUM(4L),
        CUSTOMER(5L);

        final long roleId;
        final String name = "";

        RoleValues(long roleId) {
            this.roleId = roleId;
        }

    }
}
