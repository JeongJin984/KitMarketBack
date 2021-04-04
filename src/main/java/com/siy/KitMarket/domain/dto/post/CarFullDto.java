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
public class CarFullDto extends PostDto{
    @QueryProjection
    public CarFullDto(Long id, String title, String content) {
        super(id,title,content);
    }

    @QueryProjection
    public CarFullDto(Long id, String account, String title, String content) {
        super(id,account,title, content);
    }

    public CarFullDto(Long id, String account, String title, String content,  List<Application> applications) {
        super(id,account,title,content,applications);
    }


}
