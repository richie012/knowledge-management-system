package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);

    void deleteById(Long id);

    @Query(value = "SELECT username FROM employee", nativeQuery = true)
    List<String> getEmployees();

}