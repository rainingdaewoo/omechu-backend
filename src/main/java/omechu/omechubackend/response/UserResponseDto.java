package omechu.omechubackend.response;

import lombok.Getter;
import omechu.omechubackend.entity.User;

@Getter
public class UserResponseDto {

    private String username;

    public UserResponseDto(User user) {
        this.username = user.getUsername();
    }
}
