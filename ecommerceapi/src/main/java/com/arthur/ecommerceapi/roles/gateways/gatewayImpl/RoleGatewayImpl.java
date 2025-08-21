package com.arthur.ecommerceapi.roles.gateways.gatewayImpl;

import com.arthur.ecommerceapi.customers.domain.model.Role;
import com.arthur.ecommerceapi.customers.domain.model.RoleValues;
import com.arthur.ecommerceapi.customers.exceptions.RoleNotFoundException;
import com.arthur.ecommerceapi.roles.gateways.RoleGateway;
import com.arthur.ecommerceapi.roles.gateways.mappers.RoleMapper;
import com.arthur.ecommerceapi.roles.gateways.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleGatewayImpl implements RoleGateway {

    private final RoleRepository roleRepository;
    private final RoleMapper mapper;

    @Override
    public Role findRole(final RoleValues roleValues) {
        return mapper.toDomain(roleRepository.findByName(roleValues.getName())
                .orElseThrow(() -> new RoleNotFoundException("Role not found")));
    }
}
