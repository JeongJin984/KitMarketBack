package com.siy.KitMarket.domain.entity.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.siy.KitMarket.domain.entity.Application;
import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.domain.entity.accountPost.AccountPost;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@BatchSize(size = 100)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
@ToString(of = {"id", "title", "content"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "POST")
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private String goal;

    private Long currentNumber;
    private Long MaxNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    /**
     * 작성자(Account) 연결
     */
    private String writer;

    /**
     * 참여자(Account) 연결
     */
    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private List<AccountPost> accountPosts = new ArrayList<>();

    /**
     * Application 연결
     */
    @BatchSize(size = 100)
    @OneToMany(mappedBy = "post", fetch = LAZY, cascade = ALL)
    private List<Application> applications = new ArrayList<>();

}
