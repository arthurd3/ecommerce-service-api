package com.arthur.ecommerceapi.customers.gateways.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    private String name;
//    private Set<Permission> permission;

    public enum RoleValues {

        ADMIN(1L),
        MERCHANT_PREMIUM(2L),
        MERCHANT(3L),
        VIP(4L),
        BASIC(5L);

        final long roleId;

        RoleValues(long roleId) {
            this.roleId = roleId;
        }

        public long getRoleId() {
            return roleId;
        }

    }
}
