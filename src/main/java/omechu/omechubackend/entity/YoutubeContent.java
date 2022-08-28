package omechu.omechubackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class YoutubeContent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "youtubeContent_id")
    private Long id;

    private String URL;

    private String imageURL;

    private String youtuber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public YoutubeContent() {

    }

    @Builder
    public YoutubeContent(String URL, String imageURL, String youtuber, Store store, User user) {
        this.URL      = URL;
        this.imageURL = imageURL;
        this.youtuber = youtuber;
        this.store    = store;
        this.user     = user;
    }

    public YoutubeContentEditor.YoutubeContentEditorBuilder toYoutubeContentEditor() {
        return YoutubeContentEditor.builder()
                                    .URL(URL)
                                    .imageURL(imageURL)
                                    .youtuber(youtuber);
    }

    public void editYoutubeContent(YoutubeContentEditor youtubeContentEditor) {
        this.URL      = youtubeContentEditor.getURL();
        this.imageURL = youtubeContentEditor.getImageURL();
        this.youtuber = youtubeContentEditor.getYoutuber();
    }
}
