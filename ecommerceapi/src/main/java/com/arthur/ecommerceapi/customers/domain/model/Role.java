package com.arthur.ecommerceapi.customers.domain.model;

public class Role {

    private Long roleId;
    private String name;

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public enum Values {
        ADMIN(1L),
        MERCHANT_PREMIUM(2L),
        MERCHANTS(3L),
        CUSTOMER(4L);

        Long roleId;

        Values(Long roleId) {
            this.roleId = roleId;
        }

        public Long getRoleId() {
            return this.roleId;
        }
    }
}
