package com.siy.siyresource.domain.entity.post;

import com.siy.siyresource.domain.entity.Application;
import com.siy.siyresource.domain.entity.Participants;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;


import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@BatchSize(size = 100)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "POST")
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @NotNull
    @Column(name = "post_writer")
    private String writer;

    @NotNull
    private String title;

    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @NotNull
    private Integer maxNumber;

    private Integer currentNumber;

    @NotNull
    private LocalDateTime dueDate;

    private String category;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @Enumerated(EnumType.STRING)
    private Gender qualifyGender;   //[MALE, FEMALE, NONE]

    /**
     * 참가중인 사람들
     */
    @BatchSize(size = 100)
    @OneToMany(mappedBy = "post", fetch = LAZY, cascade = ALL)
    private Set<Participants> participants = new HashSet<>();


    /**
     * Application 연결
     */
    @BatchSize(size = 100)
    @OneToMany(mappedBy = "post", fetch = LAZY, cascade = ALL)
    private Set<Application> applications = new HashSet<>();

    public void plusCurrentNumber(){
        this.currentNumber++;
    }

    public Post(@NotNull String writer, @NotNull String title, String content,
                @NotNull Integer maxNumber, Integer currentNumber,
                @NotNull LocalDateTime dueDate, String category, PostStatus postStatus,
                Gender qualifyGender) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.maxNumber = maxNumber;
        this.currentNumber = currentNumber;
        this.dueDate = dueDate;
        this.category = category;
        this.postStatus = postStatus;
        this.qualifyGender = qualifyGender;
    }
}
