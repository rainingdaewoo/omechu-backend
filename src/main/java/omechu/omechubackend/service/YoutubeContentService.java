package omechu.omechubackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.entity.Store;
import omechu.omechubackend.entity.YoutubeContent;
import omechu.omechubackend.repository.YoutubeContentRepository;
import omechu.omechubackend.repository.StoreRepository;
import omechu.omechubackend.request.YoutubeContentAndStoreCreate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class YoutubeContentService {

    private final YoutubeContentRepository youtubeContentRepository ;
    private final StoreRepository storeRepository;

    /**
     * 유튜브 영상 맛집 작성
     * @param request
     */
    public YoutubeContent writeYoutubeContent(YoutubeContentAndStoreCreate request) {

        Store store = Store.builder()
                .storeName(request.getStoreName())
                .category(request.getCategory())
                .address(request.getStoreAddress())
                .storeNaverURL(request.getStoreNaverURL())
                .hashtag(request.getHashtag())
                .build();

        storeRepository.save(store);

        log.debug("==============유튜브 영상 이미지 링크 추출==============");
        int startYoutubeId = request.getYoutubeURL().indexOf("?v=");
        int lastYoutubeId = request.getYoutubeURL().lastIndexOf("&t=");
        String youtubeId = "";

        if(lastYoutubeId == -1) {
            youtubeId = request.getYoutubeURL().substring(startYoutubeId + 3);
        } else {
            youtubeId = request.getYoutubeURL().substring(startYoutubeId + 3, lastYoutubeId);
        }

        String imageURL = "https://i1.ytimg.com/vi/"+ youtubeId + "/mqdefault.jpg";

        log.debug("==============유튜브 영상 이미지 링크 추출 끝==============");

       YoutubeContent youtubeContent = YoutubeContent.builder()
                .URL(request.getYoutubeURL())
                .youtuber(request.getYouTuber())
                .store(store)
                .imageURL(imageURL)
                .build();

        return youtubeContentRepository.save(youtubeContent);
    }

}
