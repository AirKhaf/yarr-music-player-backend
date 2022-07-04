package online.airkhavdev.yarrmusicplayerbackend.repository.common;

import online.airkhavdev.yarrmusicplayerbackend.domain.common.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}