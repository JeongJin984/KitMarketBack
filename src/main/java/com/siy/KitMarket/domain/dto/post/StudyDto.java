package com.siy.KitMarket.domain.dto.post;

import com.siy.KitMarket.domain.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;
import java.util.*;

@Data
@AllArgsConstructor
public class StudyDto {
    private String title;
    private String content;

    private List<Application> applications;

    public StudyDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}


