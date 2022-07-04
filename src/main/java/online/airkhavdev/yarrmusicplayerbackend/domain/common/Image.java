package online.airkhavdev.yarrmusicplayerbackend.domain.common;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    private String md5Hash;

    private String uploadPath;

    public Image(String md5Hash, String uploadPath) {
        this.md5Hash = md5Hash;
        this.uploadPath = uploadPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Image image = (Image) o;
        return md5Hash != null && Objects.equals(md5Hash, image.md5Hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(md5Hash);
    }
}
