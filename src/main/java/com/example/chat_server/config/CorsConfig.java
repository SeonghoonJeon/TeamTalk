// (새 파일) src/main/java/com/example/chat_server/config/CorsConfig.java
package com.example.chat_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration c = new CorsConfiguration();
        // ✅ 리액트 개발 서버 origin 허용
        c.setAllowedOrigins(List.of("http://localhost:5173"));
        // ✅ 쿠키(JSESSIONID) 전달 허용
        c.setAllowCredentials(true);
        // ✅ 메서드/헤더 허용
        c.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
        c.setAllowedHeaders(List.of("*"));
        // (선택) 클라이언트에서 읽어야 할 헤더가 있으면 노출
        // c.setExposedHeaders(List.of("Location"));

        UrlBasedCorsConfigurationSource s = new UrlBasedCorsConfigurationSource();
        s.registerCorsConfiguration("/**", c);
        return s;
    }
}
