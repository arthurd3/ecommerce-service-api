package com.arthur.ecommerceapi.security.jwt;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.domain.model.Permission;
import com.arthur.ecommerceapi.customers.domain.model.Role;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.stream.Collectors;

@Component
public class TokenGeneration {

    private final JwtEncoder jwtEncoder;
    private final Long EXPIRE_IN = 300L;

    public TokenGeneration(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(Customer customer) {
        var now = Instant.now();

        String roles = customer.getRoles().stream()
                .map(role -> "ROLE_" + role.getName())
                .collect(Collectors.joining(" "));

        String permissions = customer.getPermissions().stream()
                .map(Permission::getName)
                .collect(Collectors.joining(" "));

        String scopes = roles + " " + permissions;

        scopes = scopes.trim().replaceAll("\\s+", " ");

        var claims = JwtClaimsSet.builder()
                .issuer("ecommerceReal")
                .subject(customer.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(this.EXPIRE_IN))
                .claim("scope", scopes)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public Long getExpireIn() {
        return EXPIRE_IN;
    }
}
