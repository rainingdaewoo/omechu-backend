package omechu.omechubackend.response;

import lombok.Builder;
import lombok.Getter;
import omechu.omechubackend.entity.Request;
import omechu.omechubackend.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class RequestResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String category;
    private final String state;
   // private final Pageable pageable;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime createdDate;

    public RequestResponse(Request request) {
        this.id             = request.getId();
        this.title          = request.getTitle();
        this.content        = request.getContent();
        this.category       = request.getCategory();
        this.state          = request.getState();
        this.createdDate    = request.getCreatedDate();
    }
    @Builder
    public RequestResponse(Long id, String title, String content, String category, String state, LocalDateTime createdDate) {
        this.id             = id;
        this.title          = title.substring(0, Math.min(title.length(), 10));
        this.content        = content;
        this.category       = category;
        this.state          = state;
        this.createdDate    = createdDate;
    }
}
