package omechu.omechubackend.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "likes")
@Getter
public class Like extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Like(Store store, User user) {
        this.store = store;
        this.user = user;
    }

    public Like() {

    }
}
