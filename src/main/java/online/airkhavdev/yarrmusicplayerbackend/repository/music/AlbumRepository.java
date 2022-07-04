package online.airkhavdev.yarrmusicplayerbackend.repository.music;

import online.airkhavdev.yarrmusicplayerbackend.domain.music.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}