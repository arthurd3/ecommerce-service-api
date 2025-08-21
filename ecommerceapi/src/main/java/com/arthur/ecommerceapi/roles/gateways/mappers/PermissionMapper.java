package com.arthur.ecommerceapi.roles.gateways.mappers;

import com.arthur.ecommerceapi.customers.domain.model.Permission;
import com.arthur.ecommerceapi.customers.gateways.entities.PermissionEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PermissionMapper {
    Permission toDomain(PermissionEntity entity);

    PermissionEntity toEntity(Permission domain);
}
