package ru.richieernest.knowledgeManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;
import ru.richieernest.knowledgeManagementSystem.entity.Role;
import ru.richieernest.knowledgeManagementSystem.repository.EmployeeRepo;
import ru.richieernest.knowledgeManagementSystem.service.UserService;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/forgetPassword")
    public void forgetPassword(@RequestBody String password){
        System.out.println(password);
        userService.changePass(password);
    }

    @GetMapping("/admin")
    public void admin(){
        Employee employee = Employee.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(Collections.singleton(Role.ADMIN))
                .build();
        employeeRepo.save(employee);
    }
}
