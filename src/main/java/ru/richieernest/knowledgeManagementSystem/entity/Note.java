package ru.richieernest.knowledgeManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "note")
@Data
public class Note {
    @Id
    Long noteId;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    Category category;
    @Column(name = "name")
    String name;
    @Column(name = "content")
    String content;
}

