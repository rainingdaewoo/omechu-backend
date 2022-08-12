package omechu.omechubackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.entity.*;
import omechu.omechubackend.exception.PostNotFound;
import omechu.omechubackend.repository.StoreRepository;
import omechu.omechubackend.repository.YoutubeContentRepository;
import omechu.omechubackend.request.PostEdit;
import omechu.omechubackend.request.PostSearch;
import omechu.omechubackend.request.YoutubeContentEdit;
import omechu.omechubackend.response.StoreResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static omechu.omechubackend.entity.QStore.store;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    private final YoutubeContentRepository youtubeContentRepository ;

    /**
     * 가게 정보 전체 불러오기
     * @return
     */
    public List<StoreResponseDto> getAllYoutubeContent() {
        System.out.println("getAllYoutubeContent" + storeRepository.findAll());

        return storeRepository.findAll().stream().map(store -> new StoreResponseDto(store)).collect(Collectors.toList());
    }

    public List<StoreResponseDto> getYoutubeContent(PostSearch postSearch) {
        return storeRepository.getStoreList(postSearch).stream().map(store -> new StoreResponseDto(store)).collect(Collectors.toList());
    }


    public StoreResponseDto findById(Long id) {
        Store findStore = storeRepository.findById(id)
                .orElseThrow( ()->new IllegalArgumentException("id를 확인해주세요") );
        StoreResponseDto storeResponseDto = new StoreResponseDto(findStore);
        return storeResponseDto;
    }

    /**
     * 게시글 수정
     * @param id
     * @param youtubeContentEdit
     */
    @Transactional
    public void editYoutubeContent(Long id, YoutubeContentEdit youtubeContentEdit) {
        log.info("==============가게 정보 수정 시작 ==============");
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new PostNotFound());

        StoreEditor.StoreEditorBuilder storeEditorBuilder =  store.toStoreEditor();

        StoreEditor storeEditor = storeEditorBuilder
                .storeNaverURL(youtubeContentEdit.getStoreNaverURL())
                .storeName(youtubeContentEdit.getStoreName())
                .category(youtubeContentEdit.getCategory())
                .phone(null)
                .address(youtubeContentEdit.getStoreAddress())
                .hashtag(youtubeContentEdit.getHashtag())
                .build();

        store.editStore(storeEditor);
        log.info("==============가게 정보 수정 끝 ==============");

        log.info("==============유튜브 컨텐츠 수정 시작 ==============");
        YoutubeContent youtubeContent = youtubeContentRepository.findById(youtubeContentEdit.getYoutubeContentId())
                .orElseThrow(() -> new PostNotFound());


        log.info("==============유튜브 영상 이미지 링크 추출==============");
        int startYoutubeId = youtubeContentEdit.getYoutubeURL().indexOf("?v=");
        int lastYoutubeId = youtubeContentEdit.getYoutubeURL().lastIndexOf("&t=");
        String youtubeId = "";

        if(lastYoutubeId == -1) {
            youtubeId = youtubeContentEdit.getYoutubeURL().substring(startYoutubeId + 3);
        } else {
            youtubeId = youtubeContentEdit.getYoutubeURL().substring(startYoutubeId + 3, lastYoutubeId);
        }

        String imageURL = "https://i1.ytimg.com/vi/"+ youtubeId + "/mqdefault.jpg";

        log.info("==============유튜브 영상 이미지 링크 추출 끝==============");

        YoutubeContentEditor.YoutubeContentEditorBuilder youtubeContentEditorBuilder = youtubeContent.toYoutubeContentEditor();

        YoutubeContentEditor youtubeContentEditor = youtubeContentEditorBuilder
                .URL(youtubeContentEdit.getYoutubeURL())
                .youtuber(youtubeContentEdit.getYouTuber())
                .imageURL(imageURL)
                .build();

        youtubeContent.editYoutubeContent(youtubeContentEditor);

        log.info("==============유튜브 컨텐츠 수정 끝 ==============");
    }

    public void deleteStore(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new PostNotFound());

        storeRepository.delete(store);
    }
}
