package com.siy.KitMarket.domain.entity.accountPost;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.domain.entity.account.AccountCode;
import com.siy.KitMarket.domain.entity.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT_POST")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountPost {

    @Id
    @GeneratedValue
    @Column(name = "account_post_id")
    private Long id;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(targetEntity = Post.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "participant")
    private String username;

    @Enumerated(EnumType.STRING)
    private AccountCode code;

    public AccountPost(Post post, Account participant){
        this.account = participant;
        this.post = post;
        account.getAccountPosts().add(this);
        post.getAccountPosts().add(this);
    }

    public AccountPost(Post post, Account account, String username) {
        this.account = account;
        this.post = post;
        this.username = username;
        account.getAccountPosts().add(this);
        post.getAccountPosts().add(this);
    }
}
