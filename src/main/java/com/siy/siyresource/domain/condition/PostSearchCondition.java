package com.siy.siyresource.domain.condition;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostSearchCondition {
    private Long id;
    private String username;
    private String participantName;
}
