package com.siy.KitMarket.domain.entity.post;

import com.siy.KitMarket.domain.entity.Chat;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@BatchSize(size = 100)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
@ToString(of = {"id", "title", "content"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

/**
     * member 연결
     */
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;


    /**
     * chat 연결
     */
    @BatchSize(size = 100)
    @OneToMany(mappedBy = "post", fetch = LAZY, cascade = ALL)
    private List<Chat> chats = new ArrayList<>();

}
