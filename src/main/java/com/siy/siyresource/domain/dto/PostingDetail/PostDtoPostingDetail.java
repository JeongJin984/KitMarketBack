package com.siy.siyresource.domain.dto.PostingDetail;

import com.siy.siyresource.domain.dto.ApplicationDto;
import com.siy.siyresource.domain.dto.ParticipantsDto;
import com.siy.siyresource.domain.entity.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

@Getter
@Setter
@NoArgsConstructor
public class PostDtoPostingDetail {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private Long dueDate;
    private String deadLine;
    private String createdAt;
    private Integer maxNum;
    private Integer curNum;
    private String category;
    private String status;

    // 대기 중인사람
    private Set<ApplicationDto> applications = new HashSet<>();

    public Long calDeadLine(LocalDateTime deadLine){
        LocalDate currentDay = LocalDate.now();

        long between = DAYS.between(currentDay, deadLine);

        return between;
    }

    public PostDtoPostingDetail(Post post, Set<ApplicationDto> applications) {
        this.id = post.getId();
        this.writer = post.getWriter();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.dueDate = calDeadLine(post.getDueDate());
        this.deadLine = post.getDueDate().toString();
        this.createdAt = post.getCreatedAt().toString();
        this.maxNum = post.getMaxNumber();
        this.curNum = post.getCurrentNumber();
        this.category = post.getCategory();
        this.status = post.getPostStatus().toString();

        this.applications = applications;
    }
}
