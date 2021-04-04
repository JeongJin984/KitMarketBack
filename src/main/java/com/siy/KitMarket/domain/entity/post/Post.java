package com.siy.KitMarket.domain.entity.post;

import com.siy.KitMarket.domain.entity.Application;
import com.siy.KitMarket.domain.entity.account.Account;
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
public abstract class Post {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private String goal;

    private Long currentNumber;
    private Long MaxNumber;

    private LocalDateTime localDateTime;

    public Post(String title, String content, Account account) {
        this.title = title;
        this.content = content;
        this.account = account;
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * Account 연결
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_id")
    private Account account;


    /**
     * Application 연결
     */
    @BatchSize(size = 100)
    @OneToMany(mappedBy = "post", fetch = LAZY, cascade = ALL)
    private List<Application> applications = new ArrayList<>();

}
