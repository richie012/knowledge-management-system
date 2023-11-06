package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.entity.Category;
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
