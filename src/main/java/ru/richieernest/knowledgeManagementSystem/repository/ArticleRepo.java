package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.entity.Article;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a WHERE a.articleParentId IS NULL")
    List<Article> findAllWithNoParent();
    List<Article> findAllByArticleParentId(Long id);
    List<Article> findAll();





}
