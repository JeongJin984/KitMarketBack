package com.siy.KitMarket.domain.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.KitMarket.domain.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.*;

@Data
@NoArgsConstructor
public class StudyDto {
    private Long id;
    private String title;
    private String content;

    private List<Application> applications;

    @QueryProjection
    public StudyDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public StudyDto(Long id, String title, String content, List<Application> applications) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.applications = applications;
    }
}


