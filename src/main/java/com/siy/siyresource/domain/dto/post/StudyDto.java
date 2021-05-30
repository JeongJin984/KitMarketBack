package com.siy.siyresource.domain.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.entity.post.Study.StudyCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StudyDto extends PostDto {
    private StudyCategory subject;

    private String region;

    private String time;

    @QueryProjection
    public StudyDto(Long id, String writer, String title, String content, LocalDateTime dueDate, LocalDateTime createdAt, Integer maxNum,
                    Integer curNum, String status, String category, StudyCategory subject, String region, String time) {
        super(id, writer, title, content, dueDate, createdAt, maxNum, curNum, category, status);
        this.subject = subject;
        this.region = region;
        this.time = time;
    }
}


