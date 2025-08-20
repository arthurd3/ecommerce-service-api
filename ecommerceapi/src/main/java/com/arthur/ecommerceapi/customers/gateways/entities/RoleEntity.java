package com.arthur.ecommerceapi.customers.gateways.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<PermissionEntity> permission;

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
            this.name = "ROLE_" + name;
        }

    }

}
