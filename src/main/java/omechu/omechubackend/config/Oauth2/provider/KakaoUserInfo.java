package omechu.omechubackend.config.Oauth2.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuth2userInfo {
    private Map<String, Object> attributes; // oAuth2User.getAttributes()

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("id")) ;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        Map<String, String> kakao_account = (Map<String, String>) attributes.get("kakao_account");
        return kakao_account.get("email");
    }

    @Override
    public String getName() {
        Map<String, String> properties = (Map<String, String>) attributes.get("properties");
        return properties.get("nickname");
    }

    @Override
    public String getNickName() {
        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, String> profile       = (Map<String, String>) kakao_account.get("profile");
        return profile.get("nickname");
    }
}

