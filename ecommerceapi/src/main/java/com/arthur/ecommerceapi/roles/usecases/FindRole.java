package com.arthur.ecommerceapi.roles.usecases;

import com.arthur.ecommerceapi.customers.domain.model.Role;
import com.arthur.ecommerceapi.customers.domain.model.RoleValues;
import com.arthur.ecommerceapi.roles.gateways.RoleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindRole {

    private final RoleGateway roleGateway;

    public Role findRole(final RoleValues roleValues) {
        return roleGateway.findRole(roleValues);
    }
}
