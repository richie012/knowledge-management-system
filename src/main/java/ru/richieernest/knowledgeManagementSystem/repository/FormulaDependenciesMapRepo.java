package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.entity.FormulaDependenciesMap;

import java.util.List;
@Repository
public interface FormulaDependenciesMapRepo extends JpaRepository<FormulaDependenciesMap, Long> {
    //List<FormulaDependenciesMap> findByParentId(Long parentId);
}
