package com.arthur.ecommerceapi.login.jwtTokenGeneration;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.domain.model.Role;
import com.arthur.ecommerceapi.customers.usecases.FindCustomer;
import com.arthur.ecommerceapi.login.dtos.request.LoginRequestDTO;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.stream.Collectors;

@Component
public class TokenGeneration {

    private final FindCustomer findCustomer;
    private final JwtEncoder jwtEncoder;
    private final Long expiresIn = 300L;

    public TokenGeneration(FindCustomer findCustomer, JwtEncoder jwtEncoder) {
        this.findCustomer = findCustomer;
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(final LoginRequestDTO login) {

        var customer = this.findCustomerByEmail(login.email());

        var now = Instant.now();

        var scopes = customer.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("ecommerce-api-realidade4")
                .subject(customer.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public Customer findCustomerByEmail(final String email) {
        return findCustomer.findByEmail(email);
    }

    public Long getExpiresIn() {
        return this.expiresIn;
    }

}
