package ru.richieernest.knowledgeManagementSystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.repository.NoteRepository;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

}
