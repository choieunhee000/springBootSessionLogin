package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/publicPage")
    public String publicPage(Model model) {
        return "public"; // templates/public.html을 찾아서 렌더링
    }

    @GetMapping("/helloPage")
    public String helloPage(Model model) {
        model.addAttribute("name", "eunhee");
        return "hello"; // templates/hello.html을 찾아서 렌더링
    }

    @GetMapping("/adminPage")
    public String adminPage(Model model) {
        return "admin"; // templates/admin.html을 찾아서 렌더링
    }

    @GetMapping("/userPage")
    public String userPage(Model model) {
        return "user"; // templates/user.html을 찾아서 렌더링
    }
}
