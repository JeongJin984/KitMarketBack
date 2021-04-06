package com.siy.KitMarket.domain.dto.accountPost;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.KitMarket.domain.dto.post.PostDto;
import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.domain.entity.account.AccountCode;
import com.siy.KitMarket.domain.entity.accountPost.AccountPost;
import com.siy.KitMarket.domain.entity.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountPostDto {

    private Long postId;
    private String username;
    private AccountCode code;

    private PostDto post;

    @QueryProjection
    public AccountPostDto(Long postId, String username, AccountCode code) {
        this.postId = postId;
        this.username = username;
        this.code = code;
    }

    @QueryProjection
    public AccountPostDto(Long postId, String username, AccountCode code, String title, String content) {
        this.postId = postId;
        this.username = username;
        this.code = code;
        this.post = code.equals(AccountCode.WRITER) ?
                new PostDto(postId, username, title, content) :
                new PostDto(postId,title, content);
    }

}
