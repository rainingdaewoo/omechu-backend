package omechu.omechubackend.repository;

import omechu.omechubackend.entity.Like;
import omechu.omechubackend.entity.Store;
import omechu.omechubackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional(readOnly = true)
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndStore(User user, Store Store);
}
