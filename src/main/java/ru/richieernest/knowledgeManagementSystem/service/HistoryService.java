package ru.richieernest.knowledgeManagementSystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.richieernest.knowledgeManagementSystem.dto.TextDto;
import ru.richieernest.knowledgeManagementSystem.entity.HistoryArticle;
import ru.richieernest.knowledgeManagementSystem.repository.ArticleRepo;
import ru.richieernest.knowledgeManagementSystem.repository.HistoryRepo;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepo historyRepo;
    private final ArticleRepo articleRepo;

    public List<HistoryArticle> viewAll(TextDto textDto){
        return historyRepo.findAllByIdContent(textDto.getId_content());
    }

    @Transactional
    public void delete(TextDto textDto){
        if(historyRepo.findById(textDto.getId()).get().getContent()
                .equals(articleRepo.findById(textDto.getId_content()).get().getContent())){
            throw new RuntimeException("Вы пытаетесь удалить оригинальную статью!");
        }
        if(textDto.getId() == null){
            throw new RuntimeException("Вы не указали статью, которую нужно удалить!");
        }
        historyRepo.deleteById(textDto.getId());
    }

    @Transactional
    public void updateArticle(TextDto textDto){
        articleRepo.updateContent(historyRepo.findById(textDto.getId()).get().getContent(), textDto.getId_content());
    }

    public HistoryArticle saveVersion(TextDto textDto){
        if (!historyRepo.findAllByContent(textDto.getText()).isEmpty()){
            throw new RuntimeException("Такой текст есть уже в другой версии!");
        }
        if(textDto.getText().isEmpty() || textDto.getId_content() == null){
            throw new RuntimeException("Ваша статья пустая или не указан номер статьи!");
        }
        HistoryArticle historyArticle = HistoryArticle.builder()
                .id_content(textDto.getId_content())
                .content(textDto.getText())
                .createdAt(new Date())
                .build();
        return historyRepo.save(historyArticle);
    }
}
