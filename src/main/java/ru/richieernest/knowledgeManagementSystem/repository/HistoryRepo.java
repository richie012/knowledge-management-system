package ru.richieernest.knowledgeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.richieernest.knowledgeManagementSystem.entity.HistoryArticle;

import java.util.List;

@Repository
public interface HistoryRepo extends JpaRepository<HistoryArticle, Long> {

    @Modifying
    @Query(value = "SELECT * FROM HistoryArticle WHERE id_content = ?1",nativeQuery = true)
    List<HistoryArticle> findAllByIdContent(Long id);

}
