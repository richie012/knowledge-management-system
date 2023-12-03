package ru.richieernest.knowledgeManagementSystem.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.richieernest.knowledgeManagementSystem.dto.ArticleDto;
import ru.richieernest.knowledgeManagementSystem.entity.Article;
import ru.richieernest.knowledgeManagementSystem.repository.ArticleRepo;
@RequiredArgsConstructor
@Component
public class ArticleMapper {

    private final ArticleRepo articleRepo;
    public ArticleDto addingChildArticlesToArticle(Article article){
        System.out.println(article.toString());
        ArticleDto articleDto = ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .author(article.getAuthor())
                .createdAt(article.getCreatedAt())
                .content(article.getContent())
                .articleParentId(article.getArticleParentId())
                .childArticles(articleRepo.findArticleIdAndTitleByArticleParentId(article.getId()))
                .build();

        return articleDto;
    }
}
