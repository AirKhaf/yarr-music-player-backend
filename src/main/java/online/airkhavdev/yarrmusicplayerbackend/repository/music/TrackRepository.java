package online.airkhavdev.yarrmusicplayerbackend.repository.music;

import online.airkhavdev.yarrmusicplayerbackend.domain.music.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
}