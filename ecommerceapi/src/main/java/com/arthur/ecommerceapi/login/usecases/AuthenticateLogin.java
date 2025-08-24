package com.arthur.ecommerceapi.login.usecases;

import com.arthur.ecommerceapi.customers.usecases.ValidatorCustomer;
import com.arthur.ecommerceapi.login.dtos.request.LoginRequestDTO;
import com.arthur.ecommerceapi.login.dtos.response.LoginResponse;
import com.arthur.ecommerceapi.security.jwt.TokenGeneration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateLogin {

    private final ValidatorCustomer validatorCustomer;
    private final TokenGeneration tokenGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthenticateLogin(ValidatorCustomer validatorCustomer, TokenGeneration tokenGenerator, AuthenticationManager authenticationManager) {
        this.validatorCustomer = validatorCustomer;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse authenticateLogin(final LoginRequestDTO loginRequestDTO) {

        var customer = validatorCustomer.validateLogin(loginRequestDTO);
        var customerAuth = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());

        this.authenticationManager.authenticate(customerAuth);

        var jwtToken = tokenGenerator.generateToken(customer);
        return new LoginResponse(jwtToken , tokenGenerator.getExpireIn());
    }
}
