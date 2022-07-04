package online.airkhavdev.yarrmusicplayerbackend.domain.music;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Year;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    private String md5Hash;

    private String name;

    private Year year;

    private String uploadPath;

    private Long listenCounter;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Track track = (Track) o;
        return md5Hash != null && Objects.equals(md5Hash, track.md5Hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(md5Hash);
    }
}
