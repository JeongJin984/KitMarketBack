package com.siy.KitMarket.domain.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.KitMarket.domain.entity.accountPost.AccountPost;
import com.siy.KitMarket.domain.entity.post.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @QueryProjection
    public CarFullDto(Long id, String writer, String title, String content, LocalDateTime createdAt,
                      Integer maxNum, Integer curNum, LocalDate deadLine) {
        super(id, writer, title, content, createdAt, maxNum, curNum, deadLine);
        this.setCategory("carFool");
    }
    public CarFullDto(Post post, Set<String> list) { super(post, list); }


}
