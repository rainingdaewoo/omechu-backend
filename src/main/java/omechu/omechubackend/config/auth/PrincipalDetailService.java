package omechu.omechubackend.config.auth;

import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.entity.User;
import omechu.omechubackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 시큐리티 설정에서 .loginProcessingUrl("/login")
 * login 요청이 오면 자동으로 UserDetailsService 타입으로 Ioc 되어 있는 loadUserByUsername 함수가 실행
 */

@Service
@Slf4j
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 스프링이 로그인 요청을 가로칠 때, username, password 2개 를 가로채는데 password 부분은 알아서 처리함.
     * username이 DB에 있는지만 확인하면 됨.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username:" + username);
        User userEntity = userRepository.findByUsername(username);

        return new PrincipalDetail(userEntity);
    }
}
