package com.siy.siyresource.domain.entity.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.siy.siyresource.domain.entity.Application;
import com.siy.siyresource.domain.entity.accountPost.AccountPost;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @NotNull
    private String title;

    private String content;

    private String goal;

    /**
     * 작성자(Account) 연결
     */
    @NotNull
    @Column(name = "post_writer")
    private String writer;

    @NotNull
    private Integer currentNumber;

    @NotNull
    private Integer maxNumber;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @NotNull
    private LocalDate deadLine;

    private String category;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post(String title,
                String content,
                @NotNull String writer,
                @NotNull Integer currentNumber,
                @NotNull Integer maxNumber,
                @NotNull LocalDate deadLine) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.maxNumber = maxNumber;
        this.currentNumber = currentNumber;
        this.deadLine = deadLine;
    }
    public void increaseCurrentNumber(){
        currentNumber++;
    }

    /**
     * 참여자(Account) 연결
     */
    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private Set<AccountPost> accountPosts = new HashSet<>();

    /**
     * Application 연결
     */
    @BatchSize(size = 100)
    @OneToMany(mappedBy = "post", fetch = LAZY, cascade = ALL)
    private Set<Application> applications = new HashSet<>();


}