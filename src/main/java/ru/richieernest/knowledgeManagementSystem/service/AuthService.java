package ru.richieernest.knowledgeManagementSystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.richieernest.knowledgeManagementSystem.dto.User;
import ru.richieernest.knowledgeManagementSystem.exception.NotFoundException;
import ru.richieernest.knowledgeManagementSystem.repository.EmployeeRepo;
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

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void createUser(User user) {
       Employee employee = Employee.builder()
              .username(user.getUsername())
               .password(passwordEncoder.encode(user.getPassword()))
               .roles(Collections.singleton(Role.USER))
               .build();

       employeeRepo.save(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee userInfo = employeeRepo.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User with username %s not found", username));
        return new UserInfoDetails(userInfo);
    }

    public Optional<Employee> getEmployeeByPrincipal(Principal principal) {
        return employeeRepo.findByUsername(principal.getName());
    }
}
