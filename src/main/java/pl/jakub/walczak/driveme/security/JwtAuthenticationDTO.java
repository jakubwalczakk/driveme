package pl.jakub.walczak.driveme.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JwtAuthenticationDTO {
    private String accessToken;
    private String tokenType;
}
