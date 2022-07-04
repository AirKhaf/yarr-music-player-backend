package online.airkhavdev.yarrmusicplayerbackend.repository.music;

import online.airkhavdev.yarrmusicplayerbackend.domain.music.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}