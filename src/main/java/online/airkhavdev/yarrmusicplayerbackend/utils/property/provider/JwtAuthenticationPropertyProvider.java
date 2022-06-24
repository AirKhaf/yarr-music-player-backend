package online.airkhavdev.yarrmusicplayerbackend.utils.property.provider;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@PropertySource("classpath:authentication.properties")
@Component
@Getter
public class JwtAuthenticationPropertyProvider {
    @Value("${jwt.access.lifetime.minutes}")
    private Long accessTokenLifeTimeMinutes;

    @Value("${jwt.refresh.lifetime.minutes}")
    private Long refreshTokenLifeTimeMinutes;

    @Value("${jwt.token.issuer}")
    private String tokenIssuer;

    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    private RSAPrivateKey privateKey;

    public Long getRefreshTokenLifeTimeMillis() {
        return refreshTokenLifeTimeMinutes * 60 * 1000;
    }

    public Long getAccessTokenLifeTimeMillis() {
        return accessTokenLifeTimeMinutes * 60 * 1000;
    }
}
