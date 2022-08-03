package omechu.omechubackend.config.Oauth2.provider;

public interface OAuth2userInfo {
    String getProviderId();     // ex google, facebook
    String getProvider();       // 해당 플랫폼 고유 아이디 ex> 123412312543
    String getEmail();
    String getName();
    String getNickName();
}
