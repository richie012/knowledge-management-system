package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.dto.ArticleIdAndTitle;
import ru.richieernest.knowledgeManagementSystem.entity.Article;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a WHERE a.articleParentId IS NULL")
    List<Article> findAllWithNoParent();
    List<Article> findAll();
    @Query("SELECT new ru.richieernest.knowledgeManagementSystem.dto.ArticleIdAndTitle(a.id, a.title) FROM Article a WHERE a.articleParentId = :articleParentId")
    List<ArticleIdAndTitle> findArticleIdAndTitleByArticleParentId(@Param("articleParentId") Long articleParentId);





}
