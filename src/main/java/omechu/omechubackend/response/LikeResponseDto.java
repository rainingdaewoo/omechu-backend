package omechu.omechubackend.response;

import lombok.Getter;
import omechu.omechubackend.entity.Like;
import omechu.omechubackend.entity.YoutubeContent;

@Getter
public class LikeResponseDto {

    private Long id;
    private Long storeId;
    private Long userId;

    public LikeResponseDto() {
    }

    public LikeResponseDto(Like like) {
        this.id = like.getId();
        this.storeId = like.getStore().getId();
        this.userId = like.getUser().getId();
    }
}
