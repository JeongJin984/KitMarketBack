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
public class StudyDto extends PostDto {
    private String Study;

    @QueryProjection
    public StudyDto(Long id, String title, String content) {
        super(id,title,content);
    }

    @QueryProjection
    public StudyDto(Long id, String account, String title, String content) {
        super(id,account,title, content);
    }

    public StudyDto(Long id, String account, String title, String content,  List<Application> applications) {
        super(id,account,title,content,applications);
    }
}


