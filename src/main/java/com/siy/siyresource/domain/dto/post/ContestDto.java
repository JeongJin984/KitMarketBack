package com.siy.siyresource.domain.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.entity.post.Contest.ContestCategory;
import com.siy.siyresource.domain.entity.post.Contest.Qualification;
import com.siy.siyresource.domain.entity.post.Post;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class ContestDto extends PostDto{

    @Enumerated(EnumType.STRING)
    private ContestCategory contestCategory; // 분야별 선택 [ REPORT,IDEA,DESIGN,CHARACTER,CULTURE,UCC, EXTERNAL_ACTIVITY]

    private String hostOrganization;    // 주최기간

    @Enumerated(EnumType.STRING)
    private Qualification qualification;   //자격 [HIGHSCHOOL, COLLEGE, NONE]

    private String homepage;    //주최 관련 홈페이지

    @QueryProjection
    public ContestDto(Long id, String writer, String title, String content,
                      LocalDateTime dueDate, LocalDateTime createdAt, Integer maxNum, Integer curNum, String category, String status, ContestCategory contestCategory, String hostOrganization, Qualification qualification, String homepage) {
        super(id, writer, title, content, dueDate, createdAt, maxNum, curNum, category,status);
        this.contestCategory = contestCategory;
        this.hostOrganization = hostOrganization;
        this.qualification = qualification;
        this.homepage = homepage;
    }

}
