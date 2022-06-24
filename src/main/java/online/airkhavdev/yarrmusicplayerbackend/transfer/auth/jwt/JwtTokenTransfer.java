package online.airkhavdev.yarrmusicplayerbackend.transfer.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class JwtTokenTransfer {
    private String accessToken;
    private String refreshToken;
}
