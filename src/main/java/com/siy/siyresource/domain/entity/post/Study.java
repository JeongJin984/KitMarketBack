package com.siy.siyresource.domain.entity.post;

import com.siy.siyresource.domain.entity.account.Account;
import com.siy.siyresource.domain.entity.post.Post;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@DiscriminatorValue("Study")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study extends Post{
    // 과목
    @Enumerated(EnumType.STRING)
    private StudyCategory subject;

    private String region;

    private String time;

    @Builder
    public Study(String title, String content) {
        super(title, content);
        this.setCategory("study");
    }
    @Builder
    public Study(Post post, String category) {
        super(post.getTitle(),
                post.getContent(),
                post.getWriter(),
                post.getCurrentNumber(),
                post.getMaxNumber(),
                post.getDeadLine());
    }

    public static Study CreateStudy() {
        return new Study();
    }
}
