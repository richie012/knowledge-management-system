package ru.richieernest.knowledgeManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.richieernest.knowledgeManagementSystem.dto.User;
import ru.richieernest.knowledgeManagementSystem.service.AuthService;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserDetailsService userDetailsService;

    @PostMapping("/registration")
    public String registration(@RequestBody User user){
        authService.createUser(user);
        return "registration";
    }

    @GetMapping("/login")
    public void login(Principal principal){
        userDetailsService.loadUserByUsername(principal.getName());
    }
    @PostMapping("/logout")
    public void logout(){
    }
}
