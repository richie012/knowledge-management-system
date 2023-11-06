package ru.richieernest.knowledgeManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
@Data
@Entity
public class Category {
    @Id
    Long categoryId;

    @OneToMany(mappedBy = "category")
    ArrayList<Note> notes;

}
