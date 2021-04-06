package com.siy.KitMarket.domain.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.KitMarket.domain.entity.accountPost.AccountPost;
import com.siy.KitMarket.domain.entity.post.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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

    public CarFullDto(Post post, Set<String> list) { super(post, list); }


}
