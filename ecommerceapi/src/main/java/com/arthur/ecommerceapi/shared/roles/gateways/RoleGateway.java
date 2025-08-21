package com.arthur.ecommerceapi.shared.roles.gateways;

import com.arthur.ecommerceapi.customers.domain.model.Role;

public interface RoleGateway {
    Role findRole(String name);
}
