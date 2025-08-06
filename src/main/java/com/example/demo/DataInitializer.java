package com.example.demo;

import com.example.demo.user.Role;
import com.example.demo.user.UserEntity;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public void run(String... args) throws Exception {
        UserEntity user1 = new UserEntity();
        user1.setName("홍길동");
        user1.setUserName("hong123");
        user1.setPassword(passwordEncoder.encode("pass1"));
        user1.setEmail("hong@example.com");
        user1.setRole(Role.ROLE_USER);

        UserEntity user2 = new UserEntity();
        user2.setName("김철수");
        user2.setUserName("kimcs");
        user2.setPassword(passwordEncoder.encode("pass2"));
        user2.setEmail("kim@example.com");
        user2.setRole(Role.ROLE_USER);

        UserEntity admin = new UserEntity();
        admin.setName("admin");
        admin.setUserName("admin");
        admin.setPassword("admin");
        admin.setEmail("admin@example.com");
        admin.setRole(Role.ROLE_ADMIN);  // 관리자 권한 설정

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(admin);

        System.out.println("초기 데이터 저장 완료");
    }
}
