package com.siy.siyresource.domain.condition;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostSearchCondition {
    private Long id;
    private String username;
    private String participantName;
    private String title;
    private String status;

    public PostSearchCondition(Long id, String username, String participantName) {
        this.id = id;
        this.username = username;
        this.participantName = participantName;
    }
}
