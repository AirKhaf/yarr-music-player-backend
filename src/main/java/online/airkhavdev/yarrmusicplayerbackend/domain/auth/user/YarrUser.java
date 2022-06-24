package online.airkhavdev.yarrmusicplayerbackend.domain.auth.user;

import lombok.*;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userflag.UserFlag;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userrole.UserRole;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usr")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YarrUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    @NaturalId
    private String login;

    @ToString.Exclude
    private String password;

    @Builder.Default
    private Boolean enabled = true;

    @Builder.Default
    private Boolean banned = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "usr_id")})
    @ToString.Exclude
    @Builder.Default
    private Set<UserRole> userRoles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_flags", joinColumns = {@JoinColumn(name = "flag_id")},
            inverseJoinColumns = {@JoinColumn(name = "usr_id")})
    @ToString.Exclude
    @Builder.Default
    private Set<UserFlag> userFlags = new HashSet<>();
}
