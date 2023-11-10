package ru.richieernest.knowledgeManagementSystem.controller;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.richieernest.knowledgeManagementSystem.dto.User;
import ru.richieernest.knowledgeManagementSystem.service.AdminService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RolesAllowed({"ROLE_ADMIN"})
public class AdminController {
    
    private final AdminService adminService;

    @PostMapping("/viewEmp")
    public List<String> viewEmp(){
        return adminService.findAllEmployees();
    }

    @PostMapping("/deleteEmp")
    public String deleteEmp(@RequestBody String id){
        adminService.deleteEmployee(Long.parseLong(id));
        return id + " удалён с сервера.";
    }
    @PostMapping("/regAdmin")
    public String registration(@RequestBody User user){
        adminService.createAdmin(user);
        return "admin";
    }
}
