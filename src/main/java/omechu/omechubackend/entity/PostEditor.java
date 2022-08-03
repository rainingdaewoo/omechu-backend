package omechu.omechubackend.entity;


import lombok.Builder;
import lombok.Getter;

/**
 * 수정할 수 있는 필드만 수정하게 따로 클래스 생성.
 */
@Getter
public class PostEditor {

    private final String title;
    private final String content;

    @Builder
    public PostEditor(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
