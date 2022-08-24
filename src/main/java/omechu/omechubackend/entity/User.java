package omechu.omechubackend.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    /** API 제공자  ex) google, facebook, naver */
    private String provider;

    /** API 제공자의 PK ex) 12321453 */
    private String providerId;

    /** 로그인 ID, PK 아님
     * provider_providerId 형식으로 저장 */
    @Column(nullable = false, unique = true)
    private String username;

    /** 회원 가입 당시 email */
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Request> requests = new ArrayList<>();

    /** 회원 등급 */
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp //  시간 자동 입력, LocalDateTime으로 바꿀지 논의 필요.
    private Timestamp createDate;

    public User() {
    }

    @Builder
    public User(Long id, String provider, String providerId, String username, String email, String password, String nickname, RoleType role) {
        this.id = id;
        this.provider = provider;
        this.providerId = providerId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }
}
