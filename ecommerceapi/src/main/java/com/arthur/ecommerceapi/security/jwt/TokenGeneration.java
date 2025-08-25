package com.arthur.ecommerceapi.security.jwt;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenGeneration {

    private final JwtEncoder jwtEncoder;
    private final Long EXPIRE_IN = 300L;

    public TokenGeneration(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(final Customer customer) {

        var now = Instant.now();

        var claims = JwtClaimsSet.builder()
                .issuer("ecommerceReal")
                .subject(customer.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(this.EXPIRE_IN))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public Long getExpireIn() {
        return EXPIRE_IN;
    }
}
