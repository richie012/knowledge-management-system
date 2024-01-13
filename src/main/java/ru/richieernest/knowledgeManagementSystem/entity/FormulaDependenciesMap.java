package ru.richieernest.knowledgeManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "formula-dependencies-map")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FormulaDependenciesMap {
    @Id
    private Long id;
    private Long parentId;
    private Long childId;

}
