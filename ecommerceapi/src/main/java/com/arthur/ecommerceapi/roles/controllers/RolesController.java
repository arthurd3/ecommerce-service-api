package com.arthur.ecommerceapi.roles.controllers;

import com.arthur.ecommerceapi.roles.dtos.request.RoleRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("api/v1/admin/roles")
@RequiredArgsConstructor
public class RolesController {

    @ResponseStatus(OK)
    @PostMapping
    public void createRole(@Valid @RequestBody RoleRequestDTO roleDTO) {

    }





}
