// (새 파일) src/main/java/com/example/chat_server/config/UserConfig.java
package com.example.chat_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        // ✅ 테스트 계정: admin/1234, user/1234
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password(encoder.encode("1234")).roles("ADMIN").build(),
                User.withUsername("user").password(encoder.encode("1234")).roles("USER").build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // ✅ AuthenticationManager가 비밀번호를 검증할 때 사용
        return new BCryptPasswordEncoder();
    }
}
