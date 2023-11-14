package ru.richieernest.knowledgeManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.richieernest.knowledgeManagementSystem.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/forgetPassword")
    public void forgetPassword(@RequestBody String password){
        System.out.println(password);
        userService.changePass(password);
    }
}
