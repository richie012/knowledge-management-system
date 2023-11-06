package ru.richieernest.knowledgeManagementSystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.richieernest.knowledgeManagementSystem.dto.User;
import ru.richieernest.knowledgeManagementSystem.repository.UserRepo;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;
import ru.richieernest.knowledgeManagementSystem.entity.Role;
import ru.richieernest.knowledgeManagementSystem.service.Detail.UserInfoDetails;
import ru.richieernest.knowledgeManagementSystem.service.Interface.UserAddtion;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService, UserAddtion {

    UserRepo userRepo;
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void createUser(User user) {
        Employee employee = new Employee();
        employee.setUsername(user.getUsername());
        employee.setPassword(passwordEncoder.encode(user.getPassword()));
        employee.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userInfo = userRepo.findByUsername(username);
        return new UserInfoDetails(userInfo);
    }

    public Optional<User> getUserByPrincipal(Principal principal) {
        return userRepo.findByUsername(principal.getName());
    }
}
