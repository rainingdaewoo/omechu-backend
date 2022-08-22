package omechu.omechubackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.request.YoutubeContentCreate;
import omechu.omechubackend.service.StoreService;
import omechu.omechubackend.service.YoutubeContentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class YoutubeContentApiController {

    private final YoutubeContentService youtubeContentService;

    @PostMapping("/api/admin/youtubeContent")
    public ResponseEntity<?> postYoutubeContent(@RequestBody @Valid YoutubeContentCreate request){
        return new ResponseEntity<>(youtubeContentService.writeYoutubeContent(request), HttpStatus.CREATED); // 201
    }

//    @GetMapping("/youtubeContent")
//    public ResponseEntity<?> getAllYoutubeContent() {
//
//        return new ResponseEntity<>(storeService.getAllYoutubeContent(), HttpStatus.OK); // 200
//    }

}
