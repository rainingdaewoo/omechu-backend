package omechu.omechubackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.request.PostSearch;
import omechu.omechubackend.request.YoutubeContentEdit;
import omechu.omechubackend.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    /**
     * 모든 가게 조회
     * @return
     */
    @GetMapping("/stores")
    public ResponseEntity<?> getAllStores() {
        return new ResponseEntity<>(storeService.getAllYoutubeContent(), HttpStatus.OK); // 200
    }

    @GetMapping("/stores/test")
    public ResponseEntity<?> getStoreList(@ModelAttribute PostSearch postSearch) {
        System.out.println("postSearch:  " + postSearch.getCategory());
        System.out.println("postSearch:  " + postSearch.getKeyword());
        return new ResponseEntity<>(storeService.getYoutubeContent(postSearch), HttpStatus.OK); // 200
    }
    /**
     * 특정 가게 조회
     * @param id
     * @return
     */
    @GetMapping("/store/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(storeService.findById(id), HttpStatus.OK); // 200
    }

    /**
     * 가게 정보 수정
     * @param id
     * @param request
     */
    @PatchMapping("/store/{id}")
    public void editYoutubeContent(@PathVariable Long id, @RequestBody @Valid YoutubeContentEdit request) {
        storeService.editYoutubeContent(id, request);

    }

    /**
     * 가게 정보 정보 삭제
     * @param id
     */
    @DeleteMapping("/store/{id}")
    public void deleteYoutubeContent(@PathVariable Long id) {
        storeService.deleteStore(id);
    }
}
