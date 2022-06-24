package online.airkhavdev.yarrmusicplayerbackend.utils.initializer.auth.user;

import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userflag.UserFlag;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userflag.UserFlagName;
import online.airkhavdev.yarrmusicplayerbackend.service.auth.YarrUserService;
import online.airkhavdev.yarrmusicplayerbackend.utils.initializer.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class UserFlagInitializable implements Initializable {
    @Autowired
    YarrUserService userService;

    @Override
    public void initialize() {
        Arrays.stream(UserFlagName.values())
                .filter(flagName -> userService.getFlagByFlagName(flagName) == null)
                .forEach(flagName -> userService.saveUserFlag(new UserFlag(flagName)));
    }
}
