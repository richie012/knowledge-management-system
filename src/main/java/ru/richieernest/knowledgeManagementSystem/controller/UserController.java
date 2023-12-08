package ru.richieernest.knowledgeManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.richieernest.knowledgeManagementSystem.dto.DoublePassword;
import ru.richieernest.knowledgeManagementSystem.dto.token.JwtResponse;
import ru.richieernest.knowledgeManagementSystem.dto.token.RefreshTokenRequest;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;
import ru.richieernest.knowledgeManagementSystem.entity.RefreshToken;
import ru.richieernest.knowledgeManagementSystem.entity.Role;
import ru.richieernest.knowledgeManagementSystem.repository.EmployeeRepo;
import ru.richieernest.knowledgeManagementSystem.service.Auth.JwtService;
import ru.richieernest.knowledgeManagementSystem.service.Auth.RefreshTokenService;
import ru.richieernest.knowledgeManagementSystem.service.EmailService;
import ru.richieernest.knowledgeManagementSystem.service.UserService;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final RefreshTokenService refreshTokenService;
    @PostMapping("/forgetPassword")
    public void forgetPassword(@RequestBody String email){
        emailService.sendEmailMessage(email);
    }

    @GetMapping("/forgetPassword/{id}")
    public void changePasswordToken(@PathVariable String id){
        if (!jwtService.isTokenExpired(id)){
            System.out.println("Переход на POST");
        }
        else{
            throw new RuntimeException("Срок ссылки истёк!");
        }
    }
    @PostMapping("/forgetPassword/{id}")
    public void changePassword(@PathVariable String id, @RequestBody DoublePassword doublePassword){
        if (doublePassword.getPassword().compareTo(doublePassword.getRepeatPassword()) == 0){
            userService.changePass(doublePassword.getPassword());
        }
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
    @PostMapping("/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getEmployee)
                .map(employee -> {
                    String accessToken = jwtService.generateToken(employee.getUsername(),120000);
                    return JwtResponse.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequest.getToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException(
                        "Refresh token is not in database!"));
    }
}
