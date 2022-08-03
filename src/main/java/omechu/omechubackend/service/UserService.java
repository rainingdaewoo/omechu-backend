package omechu.omechubackend.service;

import lombok.RequiredArgsConstructor;
import omechu.omechubackend.entity.User;
import omechu.omechubackend.entity.RoleType;
import omechu.omechubackend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원 전체 조회
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 특정 회원 조회
     */
    public User findUser(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Transactional
    public User updateUser(Long id, User user) {

        User findUser = userRepository.findById(id).
                orElseThrow(
                        ()->new IllegalArgumentException("id를 확인해주세요")
                );

        findUser.setUsername(user.getUsername());
        return findUser;
    }
}
