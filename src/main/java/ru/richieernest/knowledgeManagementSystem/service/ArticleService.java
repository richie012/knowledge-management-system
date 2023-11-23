package ru.richieernest.knowledgeManagementSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.dto.ArticleDto;
import ru.richieernest.knowledgeManagementSystem.entity.Article;
import ru.richieernest.knowledgeManagementSystem.mapper.ArticleMapper;
import ru.richieernest.knowledgeManagementSystem.dto.ArticleIdAndTitle;
import ru.richieernest.knowledgeManagementSystem.repository.ArticleRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepo articleRepo;
    private final ArticleMapper articleMapper;

    private List<Article> loadAll(){
        return articleRepo.findAll();
    }

    public Iterable<Article> getAllArticles(){
        return loadAll();
    }

    //method for getting an article without a list of child articles
    public Article getArticleByIdWithoutChildList(Long id){
        Optional<Article> optionalArticle= articleRepo.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            return article;
        } else {
            return new Article();
        }
    }
    //method for getting an article with a list of child articles
    public ArticleDto getById(Long id){
        Optional<Article> optionalArticle = articleRepo.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            ArticleDto articleDto = articleMapper.addingChildArticlesToArticle(article);
            return articleDto;
        } else {
            return new ArticleDto();
        }
    }

    //method for getting the id and title of the main articles
    public List<ArticleIdAndTitle> getAllRootArticlesWithOnlyIdAndTitle(){
        return articleRepo.findAllWithNoParent()
                .stream()
                .map(article -> {
                    ArticleIdAndTitle articleIdAndTitle = new ArticleIdAndTitle();
                    articleIdAndTitle.setId(article.getId());
                    articleIdAndTitle.setTitle(article.getTitle());
                    return articleIdAndTitle;
                })
                .collect(Collectors.toList());
    }

}
