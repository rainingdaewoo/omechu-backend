package omechu.omechubackend.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class YoutubeContentEdit {

    @NotBlank(message = "가게 이름을 입력해주세요.")
    private String storeName;

    @NotBlank(message = "유튜브 링크를 입력해주세요.")
    private String youtubeURL;

    @NotBlank(message = "가게 주소를 입력해주세요.")
    private String storeAddress;

    @NotBlank(message = "해당 영상의 유튜버를 입력해주세요.")
    private String youTuber;

    @NotBlank(message = "해당 가게의 네이버 URL을 입력해주세요.")
    private String storeNaverURL;

    private Long youtubeContentId;

    public YoutubeContentEdit() {
    }

    @Builder
    public YoutubeContentEdit(String storeName, String youtubeURL, String storeAddress, String youTuber) {
        this.storeName = storeName;
        this.youtubeURL = youtubeURL;
        this.storeAddress = storeAddress;
        this.youTuber = youTuber;
    }
}
