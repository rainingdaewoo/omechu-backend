package omechu.omechubackend.controller.api;

import lombok.RequiredArgsConstructor;
import omechu.omechubackend.entity.User;
import omechu.omechubackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private UserService userService;

    @GetMapping("/api/user")
    public ResponseEntity<?> findAllUsers(){
        System.out.println("컨트롤러 진입 확인");
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK); // 200
    }


}
