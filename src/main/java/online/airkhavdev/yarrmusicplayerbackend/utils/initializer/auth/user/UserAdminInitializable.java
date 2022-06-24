package online.airkhavdev.yarrmusicplayerbackend.utils.initializer.auth.user;

import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.YarrUser;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userflag.UserFlag;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userflag.UserFlagName;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userrole.UserRole;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userrole.UserRoleName;
import online.airkhavdev.yarrmusicplayerbackend.service.auth.YarrUserService;
import online.airkhavdev.yarrmusicplayerbackend.utils.initializer.Initializable;
import online.airkhavdev.yarrmusicplayerbackend.utils.property.provider.AdminCredentialsPropertyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserAdminInitializable implements Initializable {
    @Autowired
    YarrUserService userService;

    @Autowired
    private AdminCredentialsPropertyProvider adminCredential;

    @Override
    public void initialize() {
        userService.getUsersOfFlag(UserFlagName.INITIAL_USER)
                .forEach(yarrUser -> userService.deleteUser(yarrUser));
        YarrUser user = YarrUser.builder()
                .name(adminCredential.getAdminName())
                .login(adminCredential.getAdminLogin())
                .password(adminCredential.getAdminPassword())
                .build();
        user.getUserFlags().add(userService.getFlagByFlagName(UserFlagName.INITIAL_USER));
        Arrays.stream(UserRoleName.values())
                .forEach(roleName -> user.getUserRoles().add(userService.getRoleByRoleName(roleName)));
        userService.saveUser(user);
    }
}
