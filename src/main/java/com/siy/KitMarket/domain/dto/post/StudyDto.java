package com.siy.KitMarket.domain.dto.post;

import com.siy.KitMarket.domain.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
public class StudyDto {
    private String title;
    private String content;
}


