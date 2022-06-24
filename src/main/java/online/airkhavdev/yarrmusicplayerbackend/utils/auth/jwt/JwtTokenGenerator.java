package online.airkhavdev.yarrmusicplayerbackend.utils.auth.jwt;

import online.airkhavdev.yarrmusicplayerbackend.utils.property.provider.JwtAuthenticationPropertyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenGenerator {
    @Autowired
    private JwtAuthenticationPropertyProvider jwtPropertiesProvider;

    @Autowired
    private JwtEncoder encoder;

    @Autowired
    private JwtDecoder decoder;

    public String generateAccessToken(Authentication authentication) {
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return generateAccessToken(roles, authentication.getName());
    }

    private String generateAccessToken(List<String> roles, String login) {
        Instant now = Instant.now();
        Long expiry = jwtPropertiesProvider.getAccessTokenLifeTimeMillis();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtPropertiesProvider.getTokenIssuer())
                .issuedAt(now)
                .expiresAt(now.plusMillis(expiry))
                .subject(login)
                .claim("roles", roles)
                .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateRefreshToken(Authentication authentication) {
        Instant now = Instant.now();
        Long expiry = jwtPropertiesProvider.getRefreshTokenLifeTimeMillis();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtPropertiesProvider.getTokenIssuer())
                .issuedAt(now)
                .expiresAt(now.plusMillis(expiry))
                .subject(authentication.getName())
                .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String getLoginFromToken(String refreshToken) {
        Jwt token = decoder.decode(refreshToken);
        return token.getSubject();
    }

    public String generateAccessToken(UserDetails userDetails) {
        String login = userDetails.getUsername();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return generateAccessToken(roles, login);
    }
}
