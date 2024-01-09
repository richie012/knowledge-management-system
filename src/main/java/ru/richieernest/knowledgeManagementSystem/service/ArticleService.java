package ru.richieernest.knowledgeManagementSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.dto.ArticleDto;
import ru.richieernest.knowledgeManagementSystem.dto.ArticleNode;
import ru.richieernest.knowledgeManagementSystem.entity.Article;
import ru.richieernest.knowledgeManagementSystem.mapper.ArticleMapper;
import ru.richieernest.knowledgeManagementSystem.dto.ArticleLink;
import ru.richieernest.knowledgeManagementSystem.repository.ArticleRepo;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<ArticleNode> getArticlesTree(Long id) {
        ArticleDto articleDto = getById(id);
        Long parentId = articleDto.getArticleParentId();
        ArrayDeque<ArticleDto> articleBranch = new ArrayDeque<>();
        ArrayDeque<ArticleDto> articleDeque = recursiveCreateTree(parentId, articleBranch);
        List<ArticleNode> articleNodes = articleRepo.findAllRootArticles()
                .stream()
                .map(article -> {
                    ArticleNode articleNode = new ArticleNode();
                    articleNode.setId(article.getId());
                    articleNode.setTitle(article.getTitle());
                    articleNode.setChildsArticle(articleMapper.addingChildArticlesToArticle(article).getChildArticles());
                    return articleNode;
                })
                .collect(Collectors.toList());

        Map<Long, ArticleNode> map = new HashMap<>();
        for (ArticleNode node : articleNodes) {
            map.put(node.getId(), node);
        }

        for (ArticleNode node : articleNodes) {
            addChildrenToNode(node, articleDeque, map, articleDto.getId());
        }

        return articleNodes;
    }
    private void addChildrenToNode(ArticleNode node, ArrayDeque<ArticleDto> deque, Map<Long, ArticleNode> map, Long lastArticleId) {
        while (!deque.isEmpty()) {
            ArticleDto dto = deque.pop();
            if (dto.getArticleParentId().equals(node.getId())) {
                ArticleLink link = new ArticleLink(dto.getId(), dto.getTitle());
                node.getChildsArticle().add(link);
                ArticleNode childNode = ArticleNode.builder()
                        .id(dto.getId())
                        .title(dto.getTitle())
                        .childsArticle(new ArrayList<>())
                        .build();
                if (dto.getId().equals(lastArticleId)) {
                    childNode.setArticleDto(dto);
                }
                map.put(childNode.getId(), childNode);
                addChildrenToNode(childNode, deque, map, lastArticleId);
            }
        }
    }

    //TODO RENAME
    private ArrayDeque<ArticleDto> recursiveCreateTree(Long id, ArrayDeque<ArticleDto> articles) {
        ArticleDto articleDto = getById(id);
        if (articleDto.getArticleParentId() == null) {
            return articles;
        }
        articles.addFirst(articleDto);
        return recursiveCreateTree(articleDto.getArticleParentId(), articles);
    }


    //method for getting an article with a list of child articles
    public ArticleDto getById(Long id) {
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

    public List<Article> addListOfArticle(List<Article> articles) {
        return articleRepo.saveAll(articles);
    }

    public Article updateArticle(Article article) {
        return addArticle(article);
    }

    public void deleteById(Long id) {
        articleRepo.deleteById(id);
    }
}
