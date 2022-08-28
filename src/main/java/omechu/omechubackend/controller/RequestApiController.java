package omechu.omechubackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.config.auth.PrincipalDetail;
import omechu.omechubackend.request.PostSearch;
import omechu.omechubackend.request.RequestCreate;
import omechu.omechubackend.service.RequestService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RequestApiController {

    private final RequestService requestService;

    /**
     * 요청 작성
     * @param user
     * @param requestCreate
     * @return
     */
    @PostMapping("/api/user/request")
    public ResponseEntity<?> postRequest(@AuthenticationPrincipal PrincipalDetail user, @RequestBody @Valid RequestCreate requestCreate){
        return new ResponseEntity<>(requestService.postRequest(user.getUser(), requestCreate), HttpStatus.CREATED); // 201
    }

    /**
     * 요청 게시글 리스트 조회
     * @param postSearch
     * @param pageable
     * @return
     */
    @GetMapping("/requests")
    public ResponseEntity<?> getRequestList(@ModelAttribute PostSearch postSearch, Pageable pageable){
        return new ResponseEntity<>(requestService.getRequestList(postSearch, pageable), HttpStatus.OK); // 201
    }

    /**
     * 특정 요청 게시글 조회
     * @param requestId
     * @return
     */
    @GetMapping("/api/user/request/{requestId}")
    public ResponseEntity<?> findByRequestId(@PathVariable Long requestId){
        return new ResponseEntity<>(requestService.findById(requestId), HttpStatus.OK); // 200
    }

    /**
     * 특정 요청 게시글 수정
     * @param requestId
     * @param user
     * @param request
     */
    @PatchMapping("/api/user/request/{requestId}")
    public void updateRequest(@PathVariable Long requestId,
                              @AuthenticationPrincipal PrincipalDetail user,
                              @RequestBody @Valid RequestCreate request){
        requestService.editRequest(requestId, user, request);
    }

    /**
     * 특정 요청 게시글 삭제
     * @param requestId
     * @param user
     */
    @DeleteMapping("/api/user/request/{requestId}")
    public void deleteRequest(@PathVariable Long requestId,
                              @AuthenticationPrincipal PrincipalDetail user){
        requestService.deleteRequest(requestId, user);
    }
}
