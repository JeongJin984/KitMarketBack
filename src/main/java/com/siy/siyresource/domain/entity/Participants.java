package com.siy.siyresource.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.entity.post.Post;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(of = {"content"})
@Table(name = "PARTICIPANTS")
@EntityListeners(AuditingEntityListener.class)
public class Participants {

    @Id
    @GeneratedValue
    @Column(name = "participants_id")
    private Long id;

    private String username;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    public Participants(String username, Post post) {
        this.post = post;
        this.username = username;
        post.getParticipants().add(this);
    }
}
