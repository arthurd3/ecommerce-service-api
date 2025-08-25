package com.arthur.ecommerceapi.login.controllers;

import com.arthur.ecommerceapi.customers.controllers.mappers.CustomerMapper;
import com.arthur.ecommerceapi.login.dtos.request.LoginRequestDTO;
import com.arthur.ecommerceapi.login.dtos.request.RegisterRequestDTO;
import com.arthur.ecommerceapi.login.dtos.response.LoginResponse;
import com.arthur.ecommerceapi.login.usecases.AuthenticateLogin;
import com.arthur.ecommerceapi.login.usecases.RegisterCustomer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/auth/v1")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticateLogin customerAuthenticated;
    private final RegisterCustomer registerCustomer;
    private final CustomerMapper customerMapper;

    @ResponseStatus(OK)
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequestDTO loginDTO) {
        return customerAuthenticated.authenticateLogin(loginDTO);
    }

    @ResponseStatus(CREATED)
    @PostMapping("/register")
    public LoginResponse register(@Valid @RequestBody RegisterRequestDTO dto) {
        var customerRegister = registerCustomer.registerCustomer(customerMapper.registerToDomain(dto));

        LoginRequestDTO login = new LoginRequestDTO(customerRegister.getEmail(), dto.password());

        return customerAuthenticated.authenticateLogin(login);
    }
}
