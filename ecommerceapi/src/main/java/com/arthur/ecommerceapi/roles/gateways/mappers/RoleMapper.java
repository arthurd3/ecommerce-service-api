package com.arthur.ecommerceapi.roles.gateways.mappers;

import com.arthur.ecommerceapi.customers.domain.model.Role;
import com.arthur.ecommerceapi.customers.gateways.entities.RoleEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING , uses =  {PermissionMapper.class})
public interface RoleMapper {

    Role toDomain(RoleEntity entity);

    RoleEntity toEntity(Role domain);

}
