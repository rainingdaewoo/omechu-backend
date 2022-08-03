package omechu.omechubackend.repository;

import omechu.omechubackend.entity.YoutubeContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YoutubeContentRepository extends JpaRepository<YoutubeContent, Long> {
}
