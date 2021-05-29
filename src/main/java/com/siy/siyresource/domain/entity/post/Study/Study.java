package com.siy.siyresource.domain.entity.post.Study;

import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.PostStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DiscriminatorValue("Study")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
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
                post.getDeadLine(),
                post.getPostStatus());
    }

    public Study(String title, String content, @NotNull String writer, @NotNull Integer currentNumber, @NotNull Integer maxNumber, @NotNull LocalDateTime deadLine, PostStatus status, StudyCategory subject, String region, String time) {
        super(title, content, writer, currentNumber, maxNumber, deadLine, status);
        this.subject = subject;
        this.region = region;
        this.time = time;
    }
}
