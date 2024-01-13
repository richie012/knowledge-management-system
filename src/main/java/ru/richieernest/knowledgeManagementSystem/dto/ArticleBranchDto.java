package ru.richieernest.knowledgeManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.richieernest.knowledgeManagementSystem.entity.Article;


import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ArticleBranchDto {
    private Article article;
    private List<ArticleBranchDto> childArticles;
}