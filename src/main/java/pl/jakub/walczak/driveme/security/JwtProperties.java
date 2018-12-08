package pl.jakub.walczak.driveme.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtProperties {

    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.algorithm}")
    private String algorithm;
    @Value("${jwt.token.expiration}")
    private long expirationTime;
}
