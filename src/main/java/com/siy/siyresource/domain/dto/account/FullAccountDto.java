package com.siy.siyresource.domain.dto.account;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.dto.accountPost.AccountPostDto;
import com.siy.siyresource.domain.dto.post.PostDto;
import com.siy.siyresource.domain.dto.post.postResponse.PostResponse;
import com.siy.siyresource.domain.entity.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullAccountDto {
    String username;
    String email;
    Integer age;
    String Major;


    PostResponse createdPost;
    PostResponse participated;
    PostResponse waiting;
}
