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
    public StudyDto(Long id, String writer, String title, String content, LocalDateTime createdAt,
                    Integer maxNum, Integer curNum, LocalDateTime deadLine, String category) {
        super(id, writer, title, content, createdAt, maxNum, curNum, deadLine, category);
    }

    @QueryProjection
    public StudyDto(Long id, String writer, String title, String content, LocalDateTime createdAt, Integer maxNum, Integer curNum, LocalDateTime deadLine, String category, StudyCategory subject, String region, String time) {
        super(id, writer, title, content, createdAt, maxNum, curNum, deadLine, category);
        this.subject = subject;
        this.region = region;
        this.time = time;
    }
}


