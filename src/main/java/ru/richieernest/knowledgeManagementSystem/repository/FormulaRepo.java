package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.entity.Formula;
@Repository
public interface FormulaRepo extends JpaRepository<Formula, Long> {
    Double findResultById(Long id);

}
