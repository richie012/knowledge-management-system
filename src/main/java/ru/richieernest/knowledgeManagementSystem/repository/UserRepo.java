package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUsername(String username);
}