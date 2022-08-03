package omechu.omechubackend.config.auth;

import lombok.Getter;
import omechu.omechubackend.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
 * 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
 */
@Getter
public class PrincipalDetail implements UserDetails, OAuth2User {

    private User user; // 콤포지션

    private Map<String, Object> attributes;

    /**
     * 일반 로그인
     */
    public PrincipalDetail(User user) {
        this.user = user;
    }

    /**
     * OAuth 로그인
     */
    public PrincipalDetail(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    /**
     * 계정이 가지고 있는 권한 목록을 리턴
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * 현재는 하나의 권한만 리턴함
     * 루프 돌아서 여러 개 리턴할 수 있게 변경해야함
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(() -> "" + user.getRole());

        return collectors;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 계정 만료 여부 리턴.(true: 만료안됨)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠김 여부 리턴.(true: 잠기지 않음)
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 비밀번호 만료 여부 리턴.(true: 만료 안 됨)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정 활성화(사용가능) 리턴(true: 활성화)
     */
    @Override
    public boolean isEnabled() {
        return true;
    }



    @Override
    public String getName() {
        return null;
    }
}
