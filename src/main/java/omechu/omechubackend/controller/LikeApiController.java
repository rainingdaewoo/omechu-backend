package omechu.omechubackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.config.auth.PrincipalDetail;
import omechu.omechubackend.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LikeApiController {

    private final LikeService likeService;

    @PostMapping("/like/{storeId}")
    public ResponseEntity<?> addLike(@AuthenticationPrincipal PrincipalDetail user, @PathVariable Long storeId) {

        boolean result = false;

        if (user != null) {
            result = likeService.addLike(user.getUser(), storeId);
        }

        return result ? new ResponseEntity<>(HttpStatus.CREATED)
                      : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
