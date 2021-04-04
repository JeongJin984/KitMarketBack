package com.siy.KitMarket.domain.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.KitMarket.domain.entity.Application;
import com.siy.KitMarket.domain.entity.post.CarFull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CarFullDto {
    private Long id;
    private String account;
    private String title;
    private String content;
    private List<Application> applications;

    @QueryProjection
    public CarFullDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @QueryProjection
    public CarFullDto(Long id, String account, String title,  String content) {
        this.id = id;
        this.account = account;
        this.title = title;
        this.content = content;
    }

    public CarFullDto(Long id, String account, String title, String content, List<Application> applications) {
        this.id = id;
        this.account = account;
        this.title = title;
        this.content = content;
        this.applications = applications;
    }


}
