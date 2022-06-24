package online.airkhavdev.yarrmusicplayerbackend.repository.auth.user;

import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.YarrUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YarrUserRepository extends JpaRepository<YarrUser, Long> {
    YarrUser findByLogin(String login);
}