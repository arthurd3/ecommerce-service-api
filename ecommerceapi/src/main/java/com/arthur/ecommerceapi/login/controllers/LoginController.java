package com.arthur.ecommerceapi.login.controllers;

import com.arthur.ecommerceapi.customers.usecases.ValidatorCustomer;
import com.arthur.ecommerceapi.login.dtos.request.LoginRequestDTO;
import com.arthur.ecommerceapi.login.dtos.request.RegisterRequestDTO;
import com.arthur.ecommerceapi.login.dtos.response.LoginResponse;
import com.arthur.ecommerceapi.login.usecases.CustomerAuthenticate;
import com.arthur.ecommerceapi.security.authenticated.CustomerAuthenticated;
import com.arthur.ecommerceapi.security.jwt.TokenGeneration;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/auth/v1")
@RequiredArgsConstructor
public class LoginController {

    private final CustomerAuthenticate customerAuthenticated;

    @ResponseStatus(OK)
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequestDTO loginDTO) {
        return customerAuthenticated.authenticateLogin(loginDTO);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequestDTO dto) {

    }
}
