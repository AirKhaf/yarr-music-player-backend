package online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.userrole;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import online.airkhavdev.yarrmusicplayerbackend.domain.auth.user.YarrUser;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Immutable
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(unique = true, nullable = false)
    private UserRoleName name;

    @ManyToMany(mappedBy = "userRoles", fetch = FetchType.LAZY)
    @ToString.Exclude
    private final Set<YarrUser> users = new HashSet<>();

    public UserRole(UserRoleName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        UserRole userRole = (UserRole) o;
        return name != null && Objects.equals(name, userRole.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
