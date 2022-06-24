package online.airkhavdev.yarrmusicplayerbackend.service.auth;

import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.YarrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

public class YarrUserDetailsService implements UserDetailsService {
    @Autowired
    private YarrUserService authService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        YarrUser user = authService.getUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<GrantedAuthority> authorities = user.getUserRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toSet());
        return new User(user.getLogin(), user.getPassword(), user.getEnabled(), true, true,
                !user.getBanned(), authorities);
    }
}
