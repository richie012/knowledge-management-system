package ru.richieernest.knowledgeManagementSystem.entity;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public enum Role {

    ADMIN("admin", "ROLE_ADMIN"),
    USER("user", "ROLE_USER");

    private String id;
    private String roleName;
}