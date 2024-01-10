package ru.richieernest.knowledgeManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextDto {

    private Long id;
    private Long id_content;
    private String text;
}
