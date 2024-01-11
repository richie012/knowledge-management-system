package ru.richieernest.knowledgeManagementSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.richieernest.knowledgeManagementSystem.dto.TextDto;
import ru.richieernest.knowledgeManagementSystem.entity.HistoryArticle;
import ru.richieernest.knowledgeManagementSystem.service.HistoryService;

import java.util.List;

@RestController
@RequestMapping("history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/")
    public ResponseEntity<List<HistoryArticle>> view(@RequestBody TextDto textDto){
        return new ResponseEntity<>(historyService.viewAll(textDto), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<HistoryArticle> save(@RequestBody TextDto textDto){
        return new ResponseEntity<>(historyService.saveVersion(textDto), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<HistoryArticle> update(@RequestBody TextDto textDto){
        historyService.updateArticle(textDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteArticleById(@RequestBody TextDto textDto){
        historyService.delete(textDto);
        return ResponseEntity.ok().build();
    }
}
