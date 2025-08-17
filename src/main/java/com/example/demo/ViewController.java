package com.example.demo;

import com.example.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final UserService userService;

    @GetMapping("/public")
    public String publicPage() {
        return "public"; // templates/public.html을 찾아서 렌더링
    }

    @GetMapping("/hello")
    public String helloPage(Model model) {
        model.addAttribute("name", userService.getCurrentUserName());
        return "hello"; // templates/hello.html을 찾아서 렌더링
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin"; // templates/admin.html을 찾아서 렌더링
    }

    @GetMapping("/user")
    public String userPage() {
        return "user"; // templates/user.html을 찾아서 렌더링
    }
}
