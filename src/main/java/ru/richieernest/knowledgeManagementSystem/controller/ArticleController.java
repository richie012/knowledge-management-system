package ru.richieernest.knowledgeManagementSystem.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.richieernest.knowledgeManagementSystem.dto.ArticleDto;
import ru.richieernest.knowledgeManagementSystem.dto.ArticleIdAndTitle;
import ru.richieernest.knowledgeManagementSystem.entity.Article;
import ru.richieernest.knowledgeManagementSystem.service.ArticleService;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/article")
    public ResponseEntity<List<ArticleIdAndTitle>> loadAllRootArticles(){
        List<ArticleIdAndTitle> articles = articleService.getAllRootArticlesWithOnlyIdAndTitle();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<ArticleDto> loadArticleById(@PathVariable Long id){
        return new ResponseEntity<>(articleService.getById(id), HttpStatus.OK);
    }
    //TODO контроллер для добавления массива статей
    @PostMapping("/edit/add")
    public ResponseEntity<Article> addArticle(@RequestBody ArticleDto articleDto){

        Article article = Article.builder()
                .title(articleDto.getTitle())
                .author(articleDto.getAuthor())
                .createdAt(articleDto.getCreatedAt())
                .content(articleDto.getContent())
                .articleParentId(articleDto.getArticleParentId())
                .build();
        return new ResponseEntity<>(articleService.addArticle(article), HttpStatus.OK);
    }
    @PutMapping("/edit/update")
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
    @DeleteMapping("/edit/delete/{id}")
    public ResponseEntity<Void> deleteArticleById(@PathVariable Long id){
        articleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
