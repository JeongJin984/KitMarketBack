package com.siy.siyresource.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.siy.siyresource.domain.entity.post.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "PARTICIPANT")
@EntityListeners(AuditingEntityListener.class)
// 참가자 목록
public class Participant {      //
    @Id
    @GeneratedValue
    @Column(name = "participant_id")
    private Long id;


    private String username;
    private String email;
    private Long age;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;


    public Participant(String username, String email, Long age, Post post) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.post = post;
        post.getParticipants().add(this);
    }

}
