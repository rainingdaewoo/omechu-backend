package omechu.omechubackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.config.auth.PrincipalDetail;
import omechu.omechubackend.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LikeApiController {

    private final LikeService likeService;

    /**
     * 좋아요 추가
     * @param user
     * @param storeId
     * @return
     */
    @PostMapping("/api/user/like/{storeId}")
    public ResponseEntity<?> addLike(@AuthenticationPrincipal PrincipalDetail user, @PathVariable Long storeId) {

        boolean result = false;

        if (user != null) {
            result = likeService.addLike(user.getUser(), storeId);
        }

        return result ? new ResponseEntity<>(HttpStatus.CREATED)
                      : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**\
     * 좋아요 취소
     * @param user
     * @param storeId
     * @return
     */
    @DeleteMapping("/api/user/like/{storeId}")
    public ResponseEntity<?> deleteLike(@AuthenticationPrincipal PrincipalDetail user, @PathVariable Long storeId) {

        boolean result = false;

        if (user != null) {
            result = likeService.deleteLike(user.getUser(), storeId);
        }

        return result ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
