package com.siy.siyresource.domain.dto.detail;

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
public class PostDtoDetail {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private String deadLine;
    private Long dueDate;
    private String createdAt;
    private Integer maxNum;
    private Integer curNum;
    private String category;
    private String status;

    // 참가중인사람
    private Set<ParticipantsDto> participants = new HashSet<>();
    // 대기 중인사람
    private Set<ApplicationDto> applications = new HashSet<>();

    public Long calDeadLine(LocalDateTime deadLine){
        LocalDate currentDay = LocalDate.now();

        long between = DAYS.between(currentDay, deadLine);

        return between;
    }

    public PostDtoDetail(Post post, Set<ParticipantsDto> participants, Set<ApplicationDto> applications) {
        this.id = post.getId();
        this.writer = post.getWriter();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.deadLine = post.getDueDate().toString();
        this.dueDate = calDeadLine(post.getDueDate());
        this.createdAt = post.getCreatedAt().toString();
        this.maxNum = post.getMaxNumber();
        this.curNum = post.getCurrentNumber();
        this.category = post.getCategory();
        this.status = post.getPostStatus().toString();

        this.participants = participants;
        this.applications = applications;
    }
}
