package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /*
     인증 과정 흐름 설명
     1. 사용자가 HTTP 요청을 보냄 (예: /login 경로, 로그인 폼 제출)
     2. Spring Security 설정(SecurityConfig)이 요청을 가로채고, 접근 권한을 확인함
        - 인증이 필요한 페이지라면 로그인 페이지로 리다이렉트하거나, 로그인 API 호출 대기
     3. 사용자가 입력한 아이디와 비밀번호를 제출하면
        - UserDetailsService(userDetailsService())가 DB에서 사용자 정보를 조회해서 UserDetails 객체 반환
     4. passwordEncoder가 사용자가 입력한 비밀번호와 DB에 저장된 암호화된 비밀번호를 비교(passwordEncoder.matches())
     5. 인증에 성공하면 세션이 생성되고 관리됨
     6. 세션 관련 리스너(SessionListener 등)가 세션 생성 이벤트를 감지해 로그를 남기거나 추가 처리를 수행
     7. ViewController 또는 컨트롤러가 사용자 권한을 확인하고, 권한에 맞는 페이지 또는 리소스를 보여줌
    */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/helloPage").hasAnyRole("USER","ADMIN")
                                .requestMatchers("/userPage").hasRole("USER")
                                .requestMatchers("/adminPage").hasRole("ADMIN")
                                .requestMatchers("/publicPage/**").permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .cors(Customizer.withDefaults()) //
                .csrf(CsrfConfigurer::disable);// CSRF는 개발 단계에서만 disable하세요.

        return http.build();
    }
}
