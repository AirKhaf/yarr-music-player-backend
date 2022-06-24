package online.airkhavdev.yarrmusicplayerbackend;

import online.airkhavdev.yarrmusicplayerbackend.utils.initializer.Initializer;
import online.airkhavdev.yarrmusicplayerbackend.utils.initializer.auth.user.UserAdminInitializable;
import online.airkhavdev.yarrmusicplayerbackend.utils.initializer.auth.user.UserFlagInitializable;
import online.airkhavdev.yarrmusicplayerbackend.utils.initializer.auth.user.UserRoleInitializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class YarrMediaPlayerBackendApplication {

    @Autowired
    private UserRoleInitializable userRoleInitializable;

    @Autowired
    private UserFlagInitializable userFlagInitializable;

    @Autowired
    private UserAdminInitializable userAdminInitializable;

    @Bean
    CommandLineRunner run() {
        return args -> {
            Initializer.initialize(userFlagInitializable, userRoleInitializable, userAdminInitializable);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(YarrMediaPlayerBackendApplication.class, args);
    }
}
