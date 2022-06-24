package online.airkhavdev.yarrmusicplayerbackend.utils.property.provider;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:authentication.properties")
@Component
@Getter
public class AdminCredentialsPropertyProvider {
    @Value("${admin.credentials.name}")
    private String adminName;

    @Value("${admin.credentials.login}")
    private String adminLogin;

    @Value("${admin.credentials.password}")
    private String adminPassword;
}
