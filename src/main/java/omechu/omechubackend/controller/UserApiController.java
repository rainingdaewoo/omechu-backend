package omechu.omechubackend.controller;

import lombok.RequiredArgsConstructor;
import omechu.omechubackend.entity.User;
import omechu.omechubackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @GetMapping("/api/admin/user")
    public ResponseEntity<?> findAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK); // 200
    }

    @GetMapping("/api/user/user/{id}")
    public ResponseEntity<?> findUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.findUser(id), HttpStatus.OK); // 200
    }

    @PutMapping("/api/user/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK); // 200
    }

    @GetMapping("/api/user/checkNickname/{nickname}")
    public boolean checkNickname(@PathVariable String nickname) {
        return userService.checkNickname(nickname);
    }

    @PatchMapping("/api/user/checkNickname/{userId}")
    public void updateUserDetail(@PathVariable Long userId, @RequestBody User user) {
        userService.updateUserDetail(userId, user);
    }
}
