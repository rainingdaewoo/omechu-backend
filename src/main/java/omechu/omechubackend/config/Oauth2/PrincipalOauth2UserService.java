package omechu.omechubackend.config.Oauth2;

import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.config.Oauth2.provider.GoogleUserInfo;
import omechu.omechubackend.config.Oauth2.provider.KakaoUserInfo;
import omechu.omechubackend.config.Oauth2.provider.OAuth2userInfo;
import omechu.omechubackend.config.auth.PrincipalDetail;
import omechu.omechubackend.entity.RoleType;
import omechu.omechubackend.entity.User;
import omechu.omechubackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    /**
     * 소셜 로그인 사이트로부터 받은 userRequest 데이터에 대한 후 처리 함수
     * @param userRequest
     * @return
     * @throws OAuth2AuthenticationException
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
        log.info("userRequest:" + userRequest.getClientRegistration());
        log.info("userRequest:" + userRequest.getAccessToken());


        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("userRequest:" + oAuth2User.getAttributes());

        OAuth2userInfo oAuth2userInfo = null;
        if( userRequest.getClientRegistration().getRegistrationId().equals("google") ) {

            log.info("구글 로그인 요청");
            oAuth2userInfo = new GoogleUserInfo(oAuth2User.getAttributes());

        }  else if ( userRequest.getClientRegistration().getRegistrationId().equals("kakao") ) {

            log.info("카카오 로그인 요청");
            oAuth2userInfo = new KakaoUserInfo((Map)oAuth2User.getAttributes());

        } else {
            log.info("우리 사이트는 구글, 카카오 로그인만 지원합니다.");
        }

        String provider   = oAuth2userInfo.getProvider();                             // google
        String providerId = oAuth2userInfo.getProviderId();                           // 숫자 ex) 12345566
        String username   = provider + "_" + providerId;                              // google_123456
        String password   = bCryptPasswordEncoder.encode("겟인데어");
        String email      = oAuth2userInfo.getEmail();
        RoleType role     = RoleType.ROLE_USER;

        User userEntity = userRepository.findByUsername(username);            // 수정 필요

        if( userEntity == null ) {
            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(userEntity);
            log.info(userEntity.getUsername() + " 님의 회원가입이 완료되었습니다.");
        } else {
            log.info(userEntity.getUsername() + " 님은 이미 회원가입되었습니다. ");
        }


        return new PrincipalDetail(userEntity, oAuth2User.getAttributes());
    }

}
