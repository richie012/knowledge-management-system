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
        List<Long> pathToRoot = findPathToRoot(id);
        Long rootId = pathToRoot.get(0); // Корневая статья находится в начале списка

        // Получаем список всех корневых статей
        List<Article> rootArticles = articleRepo.findAllRootArticles();

        // Строим ветку для каждой корневой статьи, если она присутствует в pathToRoot
        List<ArticleBranchDto> branches = rootArticles.stream()
                .map(rootArticle -> {
                    if (pathToRoot.contains(rootArticle.getId())) {
                        return buildBranch(rootArticle.getId(), id, pathToRoot);
                    } else {
                        ArticleBranchDto branchDto = new ArticleBranchDto();
                        branchDto.setArticle(rootArticle);
                        return branchDto;
                    }
                })
                .collect(Collectors.toList());

        return branches;
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

    private List<Long> findPathToRoot(Long id) {
        Article article = articleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Статья не найдена с id: " + id));

        List<Long> pathToRoot;
        if (article.getArticleParentId() == null) {
            // Если у статьи нет родительской статьи, значит она является корневой
            pathToRoot = new ArrayList<>();
        } else {
            // Если у статьи есть родительская статья, рекурсивно вызываем метод для родительской статьи
            pathToRoot = findPathToRoot(article.getArticleParentId());
        }
        pathToRoot.add(article.getId());
        return pathToRoot;
    }
    private ArticleBranchDto buildBranch(Long rootId, Long targetId, List<Long> pathToRoot) {
        Article rootArticle = articleRepo.findById(rootId)
                .orElseThrow(() -> new RuntimeException("Статья не найдена с id: " + rootId));

        ArticleBranchDto branchDto = new ArticleBranchDto();
        branchDto.setArticle(rootArticle);

        if (rootId.equals(targetId) || pathToRoot.contains(rootId)) {
            List<ArticleBranchDto> childBranches = articleRepo.findByArticleParentId(rootId).stream()
                    .map(childArticle -> buildBranch(childArticle.getId(), targetId, pathToRoot))
                    .collect(Collectors.toList());
            branchDto.setChildArticles(childBranches);
        }

        return branchDto;
    }
}
