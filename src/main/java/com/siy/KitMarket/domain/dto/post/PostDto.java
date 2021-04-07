package com.siy.KitMarket.domain.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.KitMarket.domain.dto.account.SimpleAccountDto;
import com.siy.KitMarket.domain.dto.accountPost.AccountPostDto;
import com.siy.KitMarket.domain.entity.accountPost.AccountPost;
import com.siy.KitMarket.domain.entity.post.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String writer;
    private String title;
    private String content;

    private LocalDateTime createdAt;
    private Integer maxNum;
    private Integer curNum;


    private Set<String> participants = new HashSet<>();

    @QueryProjection
    public PostDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @QueryProjection
    public PostDto(Long id, String writer, String title, String content) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public PostDto(Post post, Set<String> participants) {
        this.id = post.getId();
        this.writer = post.getWriter();
        this.title = post.getTitle();
        this.content = post.getContent();

        this.createdAt = post.getCreatedAt();
        this.maxNum = post.getMaxNumber();
        this.curNum = participants.size();

        this.participants = participants;
    }
}
