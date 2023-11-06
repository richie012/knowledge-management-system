package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.entity.Note;
@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
}
