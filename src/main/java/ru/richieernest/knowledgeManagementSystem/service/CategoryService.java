package ru.richieernest.knowledgeManagementSystem.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.entity.Category;
import ru.richieernest.knowledgeManagementSystem.repository.CategoryRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Iterable<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}
