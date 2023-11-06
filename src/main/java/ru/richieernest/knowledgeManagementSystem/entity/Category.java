package ru.richieernest.knowledgeManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@Entity
@NoArgsConstructor
public class Category {
    @Id
    Long categoryId;

    @OneToMany(mappedBy = "category")
    ArrayList<Note> notes;

}
