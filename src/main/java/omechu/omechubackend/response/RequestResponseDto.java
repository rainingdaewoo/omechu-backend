package omechu.omechubackend.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import omechu.omechubackend.entity.Request;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class RequestResponseDto {

    private final Long requestId;
    private final String title;
    private final String content;
    private final String category;
    private final String state;

    private final String username;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime createdDate;

    public RequestResponseDto(Request request) {
        this.requestId      = request.getId();
        this.title          = request.getTitle();
        this.content        = request.getContent();
        this.category       = request.getCategory();
        this.state          = request.getState();
        this.createdDate    = request.getCreatedDate();
        this.username       = request.getUser().getUsername();
    }

    //@Builder
    @QueryProjection
    public RequestResponseDto(Long requestId, String title, String content, String category, String state, LocalDateTime createdDate, String username) {
        this.requestId      = requestId;
        this.title          = title.substring(0, Math.min(title.length(), 10));
        this.content        = content;
        this.category       = category;
        this.state          = state;
        this.createdDate    = createdDate;
        this.username       = username;
    }

    public RequestResponseDto(RequestResponseDto request) {
        this.requestId      = request.getRequestId();
        this.title          = request.getTitle();
        this.content        = request.getContent();
        this.category       = request.getCategory();
        this.state          = request.getState();
        this.createdDate    = request.getCreatedDate();
        this.username       = request.getUsername();
    }
}
