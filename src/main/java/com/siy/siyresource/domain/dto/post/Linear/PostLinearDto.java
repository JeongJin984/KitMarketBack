package com.siy.siyresource.domain.dto.post.Linear;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostLinearDto {
    private Long id;
    private String category;
    private String title;
    private String writer;
    private String createdAt;

    @QueryProjection
    public PostLinearDto(Long id, String category, String title, String writer, LocalDateTime createdAt) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.writer = writer;
        this.createdAt = createdAt.toString();
    }
}

