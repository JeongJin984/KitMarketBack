package com.siy.siyresource.domain.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.entity.post.CarPool;
import com.siy.siyresource.domain.entity.post.Contest.Contest;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.Study;
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
    private Integer deadLine;

    private String createdAt;
    private Integer maxNum;
    private Integer curNum;

    private String category;

    private Set<String> participants = new HashSet<>();
    private Set<String> applications = new HashSet<>();


    public Integer calDeadLine(LocalDateTime deadLine){
        LocalDateTime currentDay = LocalDateTime.now();

        long between = DAYS.between(currentDay, deadLine);

        return (int)between;
    }

    public void settingCategory(Post post){
        if(post instanceof Study){
            this.category = "study";
        }
        else if( post instanceof CarPool){
            this.category = "carFool";
        }
        else if (post instanceof Contest){
            this.category = "contest";
        }
    }


    @QueryProjection
    public PostDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @QueryProjection
    public PostDto(Long id, String writer, String title, String content) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    @QueryProjection
    public PostDto(Long id, String writer, String title, String content, LocalDateTime createdAt,
                   Integer maxNum, Integer curNum, LocalDateTime deadLine, String category) {

        this.id = id;

        this.writer = writer;
        this.title = title;
        this.content = content;

        this.createdAt = createdAt.toString() == null ? null : createdAt.toString();

        this.maxNum = maxNum;
        this.curNum = curNum;

        this.deadLine = deadLine == null ? null : calDeadLine(deadLine);
        this.category = category;
    }

    public PostDto(Post post, Set<String> participants) {
        this.id = post.getId();

        this.writer = post.getWriter();
        this.title = post.getTitle();
        this.content = post.getContent();

        this.createdAt = post.getCreatedAt().toString();
        this.maxNum = post.getMaxNumber();
        this.curNum = participants.size();

        this.participants = participants;
        settingCategory(post);
    }

}
