package com.siy.KitMarket.domain.entity.post;

import com.siy.KitMarket.domain.entity.MemberPost;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Entity
@BatchSize(size = 100)
@Getter @Setter
public abstract class Post {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MemberPost> memberPosts = new ArrayList<>();

    private String title;

    private String content;

    private String goal;

    private Long currentNumber;
    private Long MaxNumber;

}
