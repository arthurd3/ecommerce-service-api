package com.arthur.ecommerceapi.shared.roles.repositories;

import com.arthur.ecommerceapi.customers.domain.model.Role;

public interface RoleGateway {
    Role findRole(String name);
}
