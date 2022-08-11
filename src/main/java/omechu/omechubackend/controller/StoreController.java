package omechu.omechubackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        log.info("==============도착 확인==============");
        System.out.println("유튜브 컨텐츠 ID: "  + request.getYoutubeContentId() );
        System.out.println("데이트 변경 : "  + request.getStoreName() );
        storeService.editYoutubeContent(id, request);

        // 업데이트 후 데이터를 프론트에 넘겨줘야 하는지
    }
}
