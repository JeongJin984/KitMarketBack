package com.siy.siyresource.domain.entity.post.Study;

import com.siy.siyresource.domain.entity.post.Gender;
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
@AllArgsConstructor
public class Study extends Post{
    // 과목
    @Enumerated(EnumType.STRING)
    private StudyCategory subject;

    private String region;

    private String duration;

    public StudyCategory stringToSubject(String subject){
        if(subject.equals("ENGLISH"))
            return StudyCategory.ENGLISH;
        else if(subject.equals("NCS"))
            return StudyCategory.NCS;
        else if(subject.equals("CERTIFICATE"))
            return StudyCategory.CERTIFICATE;
        return null;
    }


    public Study(@NotNull String writer, @NotNull String title, String content, @NotNull Integer maxNumber, Integer currentNumber,
                 @NotNull LocalDateTime dueDate, String category,
                 PostStatus postStatus, StudyCategory subject, String region, String time) {
        super(writer, title, content, maxNumber, currentNumber, dueDate, category, postStatus);
        this.subject = subject;
        this.region = region;
        this.duration = time;
        this.setCategory("Study");
    }

}
