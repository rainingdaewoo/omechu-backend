package omechu.omechubackend.service;

import lombok.RequiredArgsConstructor;
import omechu.omechubackend.entity.User;
import omechu.omechubackend.repository.UserRepository;
import omechu.omechubackend.response.UserResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public UserResponseDto findUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요"));
        UserResponseDto userResponseDto = new UserResponseDto(user);
        return userResponseDto;
    }

    @Transactional
    public User updateUser(Long id, User user) {

        User findUser = userRepository.findById(id).
                orElseThrow( ()->new IllegalArgumentException("id를 확인해주세요") );

        findUser.setUsername(user.getUsername());
        return findUser;
    }

    public boolean checkNickname(String nickname) {
        User findUser = userRepository.findByNickname(nickname);

        if (findUser == null ) {
            return true;
        }
        return false;
    }

    @Transactional
    public void updateUserDetail(Long userId, User user) {
        User findUser = userRepository.findById(userId).
                orElseThrow( ()->new IllegalArgumentException("id를 확인해주세요") );
        findUser.edit(user);
    }
}
