package online.airkhavdev.yarrmusicplayerbackend.repository.auth.user;

import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userflag.UserFlag;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userflag.UserFlagName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFlagRepository extends JpaRepository<UserFlag, Long> {
    UserFlag findByName(UserFlagName name);
}