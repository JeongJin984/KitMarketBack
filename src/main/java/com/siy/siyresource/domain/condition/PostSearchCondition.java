package com.siy.siyresource.domain.condition;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostSearchCondition {
    private Long id;
    private String writerName;
    private String participantName;
}
