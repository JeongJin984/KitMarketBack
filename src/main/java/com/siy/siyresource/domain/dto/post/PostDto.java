package com.siy.siyresource.domain.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.entity.post.CarPool.CarPool;
import com.siy.siyresource.domain.entity.post.Contest.Contest;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.Study.Study;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

@Data
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private String deadLine;
    private String dueDate;
    private String createdAt;
    private Integer maxNum;
    private Integer curNum;
    private String category;
    private String status;



    public String calDeadLine(LocalDateTime deadLine){
        LocalDateTime currentDay = LocalDateTime.now();

        long between = DAYS.between(currentDay, deadLine);

        return String.valueOf(between);
    }



    @QueryProjection
    public PostDto(Long id, String writer, String title, String content, LocalDateTime dueDate, LocalDateTime createdAt, Integer maxNum,
                   Integer curNum, String category, String status) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.deadLine = dueDate.toString() == null ? null : dueDate.toString();
        this.dueDate = deadLine == null ? null : calDeadLine(dueDate);
        this.createdAt = createdAt.toString() == null ? null : createdAt.toString();
        this.maxNum = maxNum;
        this.curNum = curNum;
        this.category = category;
        this.status = status;
    }
}
