package com.arthur.ecommerceapi.login.usecases;

import com.arthur.ecommerceapi.customers.usecases.ValidatorCustomer;
import com.arthur.ecommerceapi.login.dtos.request.LoginRequestDTO;
import com.arthur.ecommerceapi.login.dtos.response.LoginResponse;
import com.arthur.ecommerceapi.security.jwt.TokenGeneration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerAuthenticate {

    private final ValidatorCustomer validatorCustomer;
    private final TokenGeneration tokenGenerator;

    public LoginResponse authenticateLogin(final LoginRequestDTO loginRequestDTO) {
        validatorCustomer.validateLogin(loginRequestDTO);
        var jwtToken = tokenGenerator.generateToken(loginRequestDTO);
        return new LoginResponse(jwtToken , tokenGenerator.getExpireIn());
    }
}
