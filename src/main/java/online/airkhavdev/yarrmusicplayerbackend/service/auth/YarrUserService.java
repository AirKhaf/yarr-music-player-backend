package online.airkhavdev.yarrmusicplayerbackend.service.auth;

import lombok.RequiredArgsConstructor;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.YarrUser;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userflag.UserFlag;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userflag.UserFlagName;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userrole.UserRole;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userrole.UserRoleName;
import online.airkhavdev.yarrmusicplayerbackend.repository.auth.user.UserFlagRepository;
import online.airkhavdev.yarrmusicplayerbackend.repository.auth.user.UserRoleRepository;
import online.airkhavdev.yarrmusicplayerbackend.repository.auth.user.YarrUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class YarrUserService {
    @Autowired
    private final UserRoleRepository userRoleRepository;

    @Autowired
    private final YarrUserRepository yarrUserRepository;

    @Autowired
    private final UserFlagRepository userFlagRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public YarrUser getUserByLogin(String login) {
        return yarrUserRepository.findByLogin(login);
    }

    public UserRole getRoleByRoleName(UserRoleName roleName) {
        return userRoleRepository.findByName(roleName);
    }

    public UserFlag getFlagByFlagName(UserFlagName flagName) {
        return userFlagRepository.findByName(flagName);
    }

    public Set<YarrUser> getUsersOfFlag(UserFlagName flagName) {
        return new HashSet<>(userFlagRepository.findByName(flagName).getUsers());
    }

    public Set<YarrUser> getUsersOfRole(UserRoleName roleName) {
        return new HashSet<>(userRoleRepository.findByName(roleName).getUsers());
    }

    public YarrUser saveUser(YarrUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return yarrUserRepository.save(user);
    }

    public UserRole saveUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    public UserFlag saveUserFlag(UserFlag userFlag) {
        return userFlagRepository.save(userFlag);
    }

    public void deleteUser(YarrUser user) {
        yarrUserRepository.delete(user);
    }

    public Boolean deleteUser(String login) {
        YarrUser user = getUserByLogin(login);
        if (user != null) {
            yarrUserRepository.delete(user);
            return true;
        }
        return false;
    }
}
