package ru.richieernest.knowledgeManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.richieernest.knowledgeManagementSystem.entity.Category;
import ru.richieernest.knowledgeManagementSystem.service.CategoryService;


@RestController("/wiki")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/")
    public Iterable<Category> getAll(){
        return categoryService.getAllCategories();
    }
}
