package omechu.omechubackend.response;

import lombok.Builder;
import lombok.Getter;
import omechu.omechubackend.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StoreResponseDto {

    private final Long id;
    private final String storeName;
    private final String address;
    private final String storeNaverURL;
    private final String phone;
    private final List<YoutubeContentResponseDto> youtubeContents;

    public StoreResponseDto(Store store) {
        this.id = store.getId();
        this.storeName = store.getStoreName();
        this.address = store.getAddress();
        this.storeNaverURL = store.getStoreNaverURL();
        this.phone = store.getPhone();
        this.youtubeContents =store.getYoutubeContents().stream().map(youtubeContent -> new YoutubeContentResponseDto(youtubeContent)).collect(Collectors.toList());
    }


}
