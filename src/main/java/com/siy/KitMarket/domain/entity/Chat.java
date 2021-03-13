package com.siy.KitMarket.domain.entity;


import com.siy.KitMarket.domain.entity.post.Post;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"content"})
public class Chat {
    @Id
    @GeneratedValue
    @Column(name = "chat_id")
    private Long id;

    private String content;

//    @ManyToOne(fetch = LAZY)
//    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private LocalDateTime chatDate; //채팅 시간

    public Chat(String content, Post post) {
        this.content = content;
        this.post = post;
        post.getChats().add(this);
    }
}
