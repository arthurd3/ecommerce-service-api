package com.arthur.ecommerceapi.customers.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Role {

    private Long roleId;
    private String name;

    private Set<Permission> permission = new HashSet<>();

}
