package ru.richieernest.knowledgeManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.richieernest.knowledgeManagementSystem.entity.Employee;
import ru.richieernest.knowledgeManagementSystem.repository.ArticleRepo;

@SpringBootApplication
public class KnowledgeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnowledgeManagementSystemApplication.class, args);

	}

}
