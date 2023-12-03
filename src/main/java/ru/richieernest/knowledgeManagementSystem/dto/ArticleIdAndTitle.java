package ru.richieernest.knowledgeManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ArticleIdAndTitle {
    private Long id;
    private String title;
}
