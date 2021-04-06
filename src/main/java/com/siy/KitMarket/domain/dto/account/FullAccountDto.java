package com.siy.KitMarket.domain.dto.account;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.KitMarket.domain.dto.accountPost.AccountPostDto;
import com.siy.KitMarket.domain.dto.post.PostDto;
import com.siy.KitMarket.domain.dto.post.QPostDto;
import com.siy.KitMarket.domain.entity.post.Post;
import lombok.Data;

import java.util.List;

@Data
public class FullAccountDto {
    private String username;
    private String email;
    private int age;

    private List<PostDto> createdPost;
    private List<PostDto> participatingPost;

    @QueryProjection
    public FullAccountDto(String username, String email, int age, List<PostDto> joiningPost) {
        this.username = username;
        this.email = email;
        this.age = age;
    }

    @QueryProjection
    public FullAccountDto(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public FullAccountDto() {

    }
}
