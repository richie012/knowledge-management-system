package ru.richieernest.knowledgeManagementSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.dto.*;
import ru.richieernest.knowledgeManagementSystem.entity.Article;
import ru.richieernest.knowledgeManagementSystem.mapper.ArticleMapper;
import ru.richieernest.knowledgeManagementSystem.repository.ArticleRepo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepo articleRepo;
    private final ArticleMapper articleMapper;

    private List<Article> loadAll() {
        return articleRepo.findAll();
    }

    public List<Article> getAllArticles() {
        return loadAll();
    }


    public List<ArticleBranchDto> getArticleBranches(Long id) {
        // Получаем id корневой статьи
        Long rootId = findRootArticleId(id);

        // Строим ветку статей от корневой статьи до статьи с указанным id
        ArticleBranchDto targetBranch = buildArticleBranchDownwards(rootId, id);

        // Получаем список всех корневых статей
        List<ArticleBranchDto> rootArticles = articleRepo.findAllRootArticles().stream()
                .map(rootArticle -> buildArticleBranchDownwards(rootArticle.getId(), rootArticle.getId()))
                .collect(Collectors.toList());

        // Находим индекс статьи с тем же id, что и у targetBranch.article.getId() в списке rootArticles
        int index = IntStream.range(0, rootArticles.size())
                .filter(i -> rootArticles.get(i).getArticle().getId().equals(targetBranch.getArticle().getId()))
                .findFirst()
                .orElse(-1); // возвращает -1, если такой статьи нет

        // Если статья найдена, заменяем ее на targetBranch
        if (index != -1) {
            rootArticles.set(index, targetBranch);
        }

        return rootArticles;
    }

    //method for getting the id and title of the main articles
    public List<ArticleLink> getAllRootArticleLinks() {
        return articleRepo.findAllRootArticles()
                .stream()
                .map(article -> {
                    ArticleLink articleLink = new ArticleLink();
                    articleLink.setId(article.getId());
                    articleLink.setTitle(article.getTitle());
                    return articleLink;
                })
                .collect(Collectors.toList());
    }

    public Article addArticle(Article article) {
        return articleRepo.save(article);
    }

    public List<Article> addArticles(List<ArticlePostRequestDto> newArticles) {
        List<Article> articles = newArticles.stream().map(articleMapper::toArticle).collect(Collectors.toList());
        return articleRepo.saveAll(articles);
    }

    public Article updateArticle(Article article) {
        return addArticle(article);
    }

    public void deleteById(Long id) {
        articleRepo.deleteById(id);
    }
    private ArticleBranchDto buildArticleBranchDownwards(Long rootId, Long targetId) {
        Article rootArticle = articleRepo.findById(rootId)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + rootId));

        ArticleBranchDto branchDto = new ArticleBranchDto();
        branchDto.setArticle(rootArticle);

        if (!rootId.equals(targetId)) {
            List<ArticleBranchDto> childBranches = articleRepo.findByArticleParentId(rootId).stream()
                    .map(childArticle -> buildArticleBranchDownwards(childArticle.getId(), targetId))
                    .collect(Collectors.toList());
            branchDto.setChildArticles(childBranches);
        }

        return branchDto;
    }
    private Long findRootArticleId(Long id) {
        Article article = articleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));

        if (article.getArticleParentId() == null) {
            // Если у статьи нет родительской статьи, значит она является корневой
            return article.getId();
        } else {
            // Если у статьи есть родительская статья, рекурсивно вызываем метод для родительской статьи
            return findRootArticleId(article.getArticleParentId());
        }
    }
}
