package online.airkhavdev.yarrmusicplayerbackend.api.auth.jwt;

import lombok.RequiredArgsConstructor;
import online.airkhavdev.yarrmusicplayerbackend.exception.responce.BadRequestException;
import online.airkhavdev.yarrmusicplayerbackend.form.auth.LoginForm;
import online.airkhavdev.yarrmusicplayerbackend.form.auth.jwt.RefreshTokenForm;
import online.airkhavdev.yarrmusicplayerbackend.transfer.auth.jwt.JwtTokenTransfer;
import online.airkhavdev.yarrmusicplayerbackend.utils.auth.jwt.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/jwt")
public class JwtTokenController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenGenerator tokenGenerator;

    @PostMapping(value = "/refresh")
    @ResponseBody
    public ResponseEntity<JwtTokenTransfer> refreshAccessToken(@ModelAttribute RefreshTokenForm refreshTokenForm) {
        try {
            String login = tokenGenerator.getLoginFromToken(refreshTokenForm.getRefreshToken());
            UserDetails user = userDetailsService.loadUserByUsername(login);
            String accessToken = tokenGenerator.generateAccessToken(user);
            return ResponseEntity.ok().body(new JwtTokenTransfer(accessToken, refreshTokenForm.getRefreshToken()));
        } catch (BadJwtException e) {
            throw new BadRequestException("Invalid token");
        }
    }

    @PostMapping(value = "/token")
    public void fakeToken(@ModelAttribute LoginForm loginForm) {
        throw new IllegalStateException("This method shouldn't be called, it was added only to swagger doc generation" +
                ". It's implemented by Spring Security filters.");
    }
}
