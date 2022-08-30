package omechu.omechubackend.response;

import lombok.Getter;
import omechu.omechubackend.entity.User;

@Getter
public class UserResponseDto {

    private String nickname;

    public UserResponseDto(User user) {
        this.nickname = user.getNickname();
    }
}
