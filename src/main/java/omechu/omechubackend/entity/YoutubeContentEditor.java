package omechu.omechubackend.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class YoutubeContentEditor {

    private final String URL;

    private final String imageURL;

    private final String youtuber;

    @Builder
    public YoutubeContentEditor(String URL, String imageURL, String youtuber) {
        this.URL = URL;
        this.imageURL = imageURL;
        this.youtuber = youtuber;
    }
}
