package com.example.demo.security;

import com.example.demo.user.UserEntity;
import com.example.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        // DB에서 username으로 사용자 조회
        return username -> {
            UserEntity user = userRepository.findByUserName(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return user; // UserDetails 반환 (Spring Security가 인증 과정에서 사용)
        };
    }

    // BCrypt 해시 함수를 이용한 비밀번호 암호화
    // - 해시 생성 시 Salt를 사용하여 레인보우 테이블(사전 대입) 공격 방어
    // - 회원가입 시 passwordEncoder.encode()로 저장하면,
    //   Spring Security 기본 인증(formLogin/UsernamePasswordAuthenticationFilter 사용)에서는 로그인 시 자동으로 matches() 호출
    // - 프론트·백 분리(JSON 로그인) 시에는 커스텀 UsernamePasswordAuthenticationFilter 구현 후
    //   AuthenticationManager.authenticate()를 거쳐야 matches()가 자동 호출됨
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
