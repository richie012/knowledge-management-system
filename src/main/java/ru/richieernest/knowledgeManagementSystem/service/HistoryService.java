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

    public void delete(TextDto textDto){
        if(!historyRepo.findById(textDto.getId()).get().getContent()
                .equals(articleRepo.findById(textDto.getId_content()).get().getContent())){
            historyRepo.deleteById(textDto.getId());
        }
    }
    @Transactional
    public void deleteAll(Long articleId){
        historyRepo.deleleAllById(articleId);
    }
    public void updateArticle(TextDto textDto){
        articleRepo.updateContent(textDto.getText(), textDto.getId_content());
    }

    public HistoryArticle saveVersion(TextDto textDto){
        HistoryArticle historyArticle = HistoryArticle.builder()
                .id_content(textDto.getId_content())
                .content(textDto.getText())
                .createdAt(new Date())
                .build();
        return historyRepo.save(historyArticle);
    }
}
