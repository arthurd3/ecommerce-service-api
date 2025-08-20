package com.arthur.ecommerceapi.customers.gateways.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "permission")
public class PermissionEntity {

    @Id
    @Column(name = "permission_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

}
