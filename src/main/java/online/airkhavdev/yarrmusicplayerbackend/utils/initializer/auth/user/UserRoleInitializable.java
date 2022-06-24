package online.airkhavdev.yarrmusicplayerbackend.utils.initializer.auth.user;

import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userrole.UserRole;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userrole.UserRoleName;
import online.airkhavdev.yarrmusicplayerbackend.service.auth.YarrUserService;
import online.airkhavdev.yarrmusicplayerbackend.utils.initializer.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class UserRoleInitializable implements Initializable {
    @Autowired
    private YarrUserService userService;

    @Override
    public void initialize() {
        Arrays.stream(UserRoleName.values())
                .filter(roleName -> userService.getRoleByRoleName(roleName) == null)
                .forEach(roleName -> userService.saveUserRole(new UserRole(roleName)));
    }
}
