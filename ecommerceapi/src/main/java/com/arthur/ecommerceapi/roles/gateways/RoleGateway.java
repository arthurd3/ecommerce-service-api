package com.arthur.ecommerceapi.roles.gateways;

import com.arthur.ecommerceapi.customers.domain.model.Role;
import com.arthur.ecommerceapi.customers.domain.model.RoleValues;

public interface RoleGateway {
    Role findRole(final RoleValues roleValues);
}
