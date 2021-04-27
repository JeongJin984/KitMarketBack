package com.siy.siyresource.domain.dto.post.detail;

import com.siy.siyresource.domain.dto.account.AccountDto;
import com.siy.siyresource.domain.dto.post.ApplicationDto;
import com.siy.siyresource.domain.dto.post.ParticipantsDto;
import com.siy.siyresource.domain.entity.post.CarFull;
import com.siy.siyresource.domain.entity.post.Contest;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.Study;
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
    private Integer deadLine;

    private String createdAt;
    private Integer maxNum;
    private Integer curNum;

    private String category;

    private Set<ParticipantsDto> participants = new HashSet<>();
    private Set<ApplicationDto> applications = new HashSet<>();

    public Integer calDeadLine(LocalDateTime deadLine){
        LocalDate currentDay = LocalDate.now();

        long between = DAYS.between(currentDay, deadLine);

        return (int)between;
    }

    public PostDtoDetail(Post post, Set<ParticipantsDto> participants, Set<ApplicationDto> applications) {
        this.id = post.getId();
        this.writer = post.getWriter();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.deadLine = calDeadLine(post.getDeadLine());
        this.createdAt = post.getCreatedAt().toString();
        this.maxNum = post.getMaxNumber();
        this.curNum = post.getCurrentNumber();
        this.category = post.getCategory();

        this.participants = participants;
        this.applications = applications;
    }
}
