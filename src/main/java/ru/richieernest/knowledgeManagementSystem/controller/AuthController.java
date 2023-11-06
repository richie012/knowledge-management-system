package ru.richieernest.knowledgeManagementSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.richieernest.knowledgeManagementSystem.dto.User;

@RestController
public class AuthController {
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user){
        return "login";
    }
}
