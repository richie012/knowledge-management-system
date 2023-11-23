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
        ArticleDto articleDto = new ArticleDto();
        articleDto.setChildArticles(articleRepo.findAllByArticleParentId(article.getArticleParentId()));
        return articleDto;
    }
}
