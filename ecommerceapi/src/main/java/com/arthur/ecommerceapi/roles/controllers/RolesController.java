package com.arthur.ecommerceapi.roles.controllers;

import com.arthur.ecommerceapi.roles.dtos.request.RoleRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class RolesController {

    public void createRole(@Valid @RequestBody RoleRequestDTO role) {

    }





}
