package omechu.omechubackend.response;

import lombok.Builder;
import lombok.Getter;
import omechu.omechubackend.entity.YoutubeContent;

@Getter
public class YoutubeContentResponseDto {

    private Long id;
    private String URL;
    private String imageURL;
    private String youtuber;
    private Long storeId;

    public YoutubeContentResponseDto() {
    }

    public YoutubeContentResponseDto(YoutubeContent youtubeContent) {
        this.id = youtubeContent.getId();
        this.URL = youtubeContent.getURL();
        this.imageURL = youtubeContent.getImageURL();
        this.youtuber = youtubeContent.getYoutuber();
        this.storeId = youtubeContent.getStore().getId();
    }
}
