package omechu.omechubackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

       /* config.setAllowCredentials(true);                       // 내 서버가 응답할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정하는 것
        config.addAllowedOrigin("http://localhost:3000");       // 모든 IP 응답 허용
        config.addAllowedHeader("*");                           // 모든 header 응답 허용
        config.addAllowedMethod("*");       */                    // 모든 method 허용 -> post, get, put, delete, patch 등

        //config.addAllowedOriginPattern("*");

        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("https://master.d1y7zww8xijf8t.amplifyapp.com");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        config.setAllowCredentials(true);

        config.addExposedHeader("Authorization");
        config.addExposedHeader("refreshToken");

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);

    }
}
