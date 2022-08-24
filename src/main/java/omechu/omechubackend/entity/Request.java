package omechu.omechubackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Request extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    private String title;

    private String category;

    @Lob
    private String content;

    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Request(String title, String category, String content, String state, User user) {
        this.title    = title;
        this.category = category;
        this.content  = content;
        this.state    = state;
        this.user     = user;
    }

    public Request() {

    }
}
