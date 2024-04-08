package ru.richieernest.knowledgeManagementSystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.dto.User;
import ru.richieernest.knowledgeManagementSystem.dto.UserRole;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;
import ru.richieernest.knowledgeManagementSystem.entity.Role;
import ru.richieernest.knowledgeManagementSystem.repository.EmployeeRepo;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;
    public List<String> findAllEmployees(){
        return employeeRepo.getEmployees();
    }
    @Transactional
    public void deleteEmployee(Long id){
        employeeRepo.deleteById(id);
    }

    @Transactional
    public void createAdmin(User user) {
        Employee employee = Employee.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(Collections.singleton(Role.ADMIN))
                .build();

        employeeRepo.save(employee);
    }
    @Transactional
    public void changeUserRole(UserRole userRole){
        System.out.println(userRole.getId() + " " + userRole.getRole());
        employeeRepo.updateRole(userRole.getId(),userRole.getRole());
    }
}
