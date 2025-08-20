package com.arthur.ecommerceapi.customers.gateways.entities;

import jakarta.persistence.*;

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
