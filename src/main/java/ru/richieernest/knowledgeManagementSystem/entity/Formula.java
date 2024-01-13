package ru.richieernest.knowledgeManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "formula")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Formula {
    @Id
    private Long id;

    private String title;

    private String formula;

    private Double result;

}
