package online.airkhavdev.yarrmusicplayerbackend.repository.auth.user;

import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userrole.UserRole;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userrole.UserRoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByName(UserRoleName name);
}