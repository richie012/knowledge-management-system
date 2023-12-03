package ru.richieernest.knowledgeManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.richieernest.knowledgeManagementSystem.entity.Article;

import java.util.List;

@Data
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ArticleDto {
    private Long id;
    private String title;
    private String author;
    private String createdAt;
    private String content;
    private Long articleParentId;

    private List<ArticleIdAndTitle> childArticles;
}
