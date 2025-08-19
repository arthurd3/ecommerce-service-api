package com.arthur.ecommerceapi.login.controllers;

import com.arthur.ecommerceapi.customers.usecases.CreateCustomer;
import com.arthur.ecommerceapi.customers.usecases.FindCustomer;
import com.arthur.ecommerceapi.customers.usecases.ValidatorCustomer;
import com.arthur.ecommerceapi.login.dtos.request.LoginRequestDTO;
import com.arthur.ecommerceapi.login.dtos.request.RegisterRequestDTO;
import com.arthur.ecommerceapi.login.dtos.response.LoginResponse;
import com.arthur.ecommerceapi.login.jwtTokenGeneration.TokenGeneration;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final ValidatorCustomer validatorCustomer;
    private final TokenGeneration tokenGenerator;

    @ResponseStatus(OK)
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequestDTO loginDTO) {
        validatorCustomer.validateLogin(loginDTO);
        var jwtToken = tokenGenerator.generateToken();
        return new LoginResponse(jwtToken , tokenGenerator.getExpiresIn());
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public void register(@Valid @RequestBody RegisterRequestDTO dto) {

    }
}
