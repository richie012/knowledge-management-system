package ru.richieernest.knowledgeManagementSystem.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ArticleIdAndTitle {
    private Long id;
    private String title;
}
