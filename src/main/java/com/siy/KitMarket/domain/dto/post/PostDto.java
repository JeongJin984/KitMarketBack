package com.siy.KitMarket.domain.dto.post;

import com.siy.KitMarket.domain.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.*;

@Data
@AllArgsConstructor
public class PostDto {
    private String title;
    private String content;

    //private List<Application> applications;

}
