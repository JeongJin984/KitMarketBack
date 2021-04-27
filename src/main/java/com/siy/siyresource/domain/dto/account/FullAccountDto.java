package com.siy.siyresource.domain.dto.account;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.dto.accountPost.AccountPostDto;
import com.siy.siyresource.domain.dto.post.PostDto;
import com.siy.siyresource.domain.entity.post.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FullAccountDto {
    private String username;
    private String email;
    private int age;

    private List<PostDto> createdPost;
    private List<PostDto> participatingPost;

    @QueryProjection
    public FullAccountDto(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }
}
