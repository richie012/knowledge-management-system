package ru.richieernest.knowledgeManagementSystem.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.richieernest.knowledgeManagementSystem.dto.*;
import ru.richieernest.knowledgeManagementSystem.entity.Article;
import ru.richieernest.knowledgeManagementSystem.service.ArticleService;
import ru.richieernest.knowledgeManagementSystem.service.HistoryService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final HistoryService historyService;

    @GetMapping("/")
    public ResponseEntity<List<ArticleLink>> loadAllRootArticles(){
        List<ArticleLink> articles = articleService.getAllRootArticleLinks();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
    //TODO RENAME BLYAT upload a branch before the article on idi
    @GetMapping("/{id}")
    public ResponseEntity<List<ArticleBranchDto>> load(@PathVariable Long id){
        return new ResponseEntity<>(articleService.getArticleBranches(id), HttpStatus.OK);
    }
    //TODO контроллер для добавления массива статей
    @PostMapping("/add")
    public ResponseEntity<List<Article>> addArticles(@RequestBody List<ArticlePostRequestDto> articlePostRequestDto){
        List<Article> articles = articleService.addArticles(articlePostRequestDto);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Article> addArticle(@RequestBody ArticlePostRequestDto articlePostRequestDto){

        Article article = Article.builder()
                .title(articlePostRequestDto.getTitle())
                .author(articlePostRequestDto.getAuthor())
                .createdAt(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'").format(new Date()).toString())
                .content(articlePostRequestDto.getContent())
                .articleParentId(articlePostRequestDto.getArticleParentId())
                .build();
        return new ResponseEntity<>(articleService.addArticle(article), HttpStatus.OK);
    }
    @PutMapping("/")
    public ResponseEntity<Article> updateArticle(@RequestBody ArticleDto articleDto){
        Article article = Article.builder()
                .id(articleDto.getId())
                .title(articleDto.getTitle())
                .author(articleDto.getAuthor())
                .createdAt(articleDto.getCreatedAt())
                .content(articleDto.getContent())
                .build();
        return new ResponseEntity<>(articleService.updateArticle(article), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticleById(@PathVariable Long id){
        historyService.deleteAll(id);
        articleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}