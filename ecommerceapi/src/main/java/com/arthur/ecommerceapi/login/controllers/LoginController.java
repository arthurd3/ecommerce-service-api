package com.arthur.ecommerceapi.login.controllers;

import com.arthur.ecommerceapi.login.dtos.request.LoginRequestDTO;
import com.arthur.ecommerceapi.login.dtos.request.RegisterRequestDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @ResponseStatus(OK)
    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginRequestDTO dto) {

    }

    @ResponseStatus(CREATED)
    @PostMapping
    public void register(@Valid @RequestBody RegisterRequestDTO dto) {

    }
}
