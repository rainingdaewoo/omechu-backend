package omechu.omechubackend.repository;

import omechu.omechubackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // SELECT u
    // FROM User u
    // WHERE u.username = ?
   User findByUsername(String username);
   User findByEmail(String email);

}
