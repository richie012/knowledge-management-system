package ru.richieernest.knowledgeManagementSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.repository.EmployeeRepo;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void changePass(String password){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var name = auth.getName();
        var pass = passwordEncoder.encode(password);
        System.out.println(pass);
        employeeRepo.updatePass(pass, name);
    }
}
