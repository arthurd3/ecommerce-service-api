package com.arthur.ecommerceapi.login.usecases;

import com.arthur.ecommerceapi.customers.usecases.FindCustomer;
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
    private final FindCustomer findCustomer;
    private final AuthenticationManager authenticationManager;

    public AuthenticateLogin(ValidatorCustomer validatorCustomer, TokenGeneration tokenGenerator, FindCustomer findCustomer, AuthenticationManager authenticationManager) {
        this.validatorCustomer = validatorCustomer;
        this.tokenGenerator = tokenGenerator;
        this.findCustomer = findCustomer;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse authenticateLogin(final LoginRequestDTO loginRequestDTO) {
        validatorCustomer.validateLogin(loginRequestDTO);

        var customer = findCustomer.findByEmail(loginRequestDTO.email());

        var customerAuth = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());

        this.authenticationManager.authenticate(customerAuth);

        var jwtToken = tokenGenerator.generateToken(customer);
        return new LoginResponse(jwtToken , tokenGenerator.getExpireIn());
    }
}
