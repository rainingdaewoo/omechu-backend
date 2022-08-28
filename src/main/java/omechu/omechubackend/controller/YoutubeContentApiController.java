package omechu.omechubackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.service.YoutubeContentService;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class YoutubeContentApiController {

    private final YoutubeContentService youtubeContentService;

//    @PostMapping("/api/admin/youtubeContent")
//    public ResponseEntity<?> postYoutubeContent(@RequestBody @Valid YoutubeContentAndStoreCreate request){
//        return new ResponseEntity<>(youtubeContentService.writeYoutubeContent(request), HttpStatus.CREATED); // 201
//    }

}
