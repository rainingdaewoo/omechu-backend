package omechu.omechubackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.config.auth.PrincipalDetail;
import omechu.omechubackend.request.PostSearch;
import omechu.omechubackend.request.YoutubeContentAndStoreCreate;
import omechu.omechubackend.request.YoutubeContentEdit;
import omechu.omechubackend.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StoreApiController {

    private final StoreService storeService;



    /**
     * 모든 가게 조회
     * @param postSearch
     * @return
     */
    @GetMapping("/stores")
    public ResponseEntity<?> getStoreList(@ModelAttribute PostSearch postSearch) {
        return new ResponseEntity<>(storeService.getYoutubeContent(postSearch), HttpStatus.OK); // 200
    }

    /**
     * 특정 가게 조회
     * @param id
     * @return
     */
    @GetMapping("/api/admin/store/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(storeService.findById(id), HttpStatus.OK); // 200
    }

    /**
     * 가게 정보 및 유튜브 컨텐츠 수정
     * @param id
     * @param request
     */
    @PatchMapping("/api/admin/store/{id}")
    public void editYoutubeContent(@PathVariable Long id, @RequestBody @Valid YoutubeContentEdit request) {
        storeService.editYoutubeContent(id, request);

    }

    /**
     * 가게 정보 및 유튜브 컨텐츠 삭제
     * @param id
     */
    @DeleteMapping("/api/admin/store/{id}")
    public void deleteYoutubeContent(@PathVariable Long id) {
        storeService.deleteStore(id);
    }

    /**
     * 가게 정보 및 유튜브 컨텐츠 생성
     * @param user
     * @param request
     */
    @PostMapping("/api/admin/store")
    public void postYoutubeContent(@AuthenticationPrincipal PrincipalDetail user,
                                   @RequestBody @Valid YoutubeContentAndStoreCreate request){
        storeService.postYoutubeContentAndStore(user.getUser(),request);
    }

    @GetMapping("/health")
    public ResponseEntity<?> test() {
        return new ResponseEntity<>(storeService.getAllYoutubeContent(), HttpStatus.OK); }// 200

    @GetMapping("/")
    public ResponseEntity<?> test2() {
        return new ResponseEntity<>(storeService.getAllYoutubeContent(), HttpStatus.OK); }// 200

}
