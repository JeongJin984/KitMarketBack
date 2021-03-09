package com.siy.KitMarket.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.siy.KitMarket.domain.entity.post.Post;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class MemberPost {
    @Id @GeneratedValue
    @Column(name = "member_post_id")
    private Long id;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
