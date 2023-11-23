package ru.richieernest.knowledgeManagementSystem.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.richieernest.knowledgeManagementSystem.dto.ArticleDto;
import ru.richieernest.knowledgeManagementSystem.dto.ArticleIdAndTitle;
import ru.richieernest.knowledgeManagementSystem.service.ArticleService;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/root")
    public ResponseEntity<List<ArticleIdAndTitle>> loadAllRootArticles(){
        List<ArticleIdAndTitle> articles = articleService.getAllRootArticlesWithOnlyIdAndTitle();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/root/{id}")
    public ResponseEntity<ArticleDto> loadArticleById(@PathVariable Long id){
        return new ResponseEntity<>(articleService.getById(id), HttpStatus.OK);
    }

}
