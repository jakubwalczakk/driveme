package pl.jakub.walczak.driveme.security;

import io.jsonwebtoken.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.jakub.walczak.driveme.model.user.User;
import pl.jakub.walczak.driveme.repos.user.UserRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.jakub.walczak.driveme.security.JwtConstants.JWT_SCOPES;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String jwtSecret;

    @Value("${jwt.token.expiration}")
    private long jwtExpirationTime;

    public String generateToken(@NonNull final Authentication authentication) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = now.plusSeconds(jwtExpirationTime);

        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(JWT_SCOPES,authorities)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant()))
                .compact();
    }

    public String getUserEmailFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

}
