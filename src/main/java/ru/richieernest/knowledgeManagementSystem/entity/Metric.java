package ru.richieernest.knowledgeManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "metric")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Metric {
    @Id
    private Long id;

    private String name;

    private Long value;

    private String formula;
    //TODO прописать джоин
    @OneToMany
    private List<Metric> children;

}
