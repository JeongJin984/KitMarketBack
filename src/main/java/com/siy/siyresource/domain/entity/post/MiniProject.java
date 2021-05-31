package com.siy.siyresource.domain.entity.post;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DiscriminatorValue("MiniProject")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class MiniProject extends Post{
    private String duration;
    private String subject;

    public MiniProject(@NotNull String writer, @NotNull String title, String content, @NotNull Integer maxNumber, Integer currentNumber, @NotNull LocalDateTime dueDate, String category, PostStatus postStatus, Gender qualifyGender, String duration, String subject) {
        super(writer, title, content, maxNumber, currentNumber, dueDate, category, postStatus);
        this.duration = duration;
        this.subject = subject;
        this.setCategory("MiniProject");
    }
}
