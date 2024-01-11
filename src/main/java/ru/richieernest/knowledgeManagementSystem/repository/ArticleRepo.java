package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.dto.ArticleLink;
import ru.richieernest.knowledgeManagementSystem.entity.Article;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a WHERE a.articleParentId IS NULL")
    List<Article> findAllRootArticles();
    List<Article> findAll();
    @Query("SELECT new ru.richieernest.knowledgeManagementSystem.dto.ArticleLink(a.id, a.title) FROM Article a WHERE a.articleParentId = :articleParentId")
    List<ArticleLink> findArticleIdAndTitleByArticleParentId(@Param("articleParentId") Long articleParentId);

    @Query("SELECT a.articleParentId FROM Article a WHERE a.id = :id")
    Long findArticleParentIdById(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE content FROM Article WHERE id = ?1", nativeQuery = true)
    void updateContent(Long id);




}
