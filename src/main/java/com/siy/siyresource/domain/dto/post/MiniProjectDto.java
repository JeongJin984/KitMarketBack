package com.siy.siyresource.domain.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MiniProjectDto extends PostDto {

    private String projectDuration;
    private String topic;

    @QueryProjection
    public MiniProjectDto(Long id, String writer, String title, String content, LocalDateTime dueDate, LocalDateTime createdAt,
                          Long maxNum, Long curNum, String category, String status, String duration, String subject) {
        super(id, writer, title, content, dueDate, createdAt, maxNum, curNum, category,status);
        this.projectDuration = duration;
        this.topic = subject;
    }


}
