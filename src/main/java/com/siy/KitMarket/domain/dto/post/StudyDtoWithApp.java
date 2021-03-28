package com.siy.KitMarket.domain.dto.post;

import com.siy.KitMarket.domain.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class StudyDtoWithApp {
    private String title;
    private String content;

    private Set<Application> application;
}

