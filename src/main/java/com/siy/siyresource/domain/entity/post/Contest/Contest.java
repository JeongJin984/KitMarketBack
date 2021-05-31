package com.siy.siyresource.domain.entity.post.Contest;

import com.siy.siyresource.domain.entity.post.Gender;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.PostStatus;
import com.siy.siyresource.domain.entity.post.Study.Study;
import com.siy.siyresource.domain.entity.post.Study.StudyCategory;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DiscriminatorValue("Contest")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Contest extends Post {

    @Enumerated(EnumType.STRING)
    private ContestCategory contestCategory; // 분야별 선택 [ REPORT,IDEA,DESIGN,CHARACTER,CULTURE,UCC, EXTERNAL_ACTIVITY]

    private String hostOrganization;    // 주최기간

    @Enumerated(EnumType.STRING)
    private Qualification qualification;   //자격 [HIGHSCHOOL, COLLEGE, NONE]

    private String homepage;    //주최 관련 홈페이지


    public Contest(@NotNull String writer, @NotNull String title, String content, @NotNull Integer maxNumber, Integer currentNumber, @NotNull LocalDateTime dueDate, String category, PostStatus postStatus, Gender qualifyGender, ContestCategory contestCategory, String hostOrganization, Qualification qualification, String homepage) {
        super(writer, title, content, maxNumber, currentNumber, dueDate, category, postStatus, qualifyGender);
        this.contestCategory = contestCategory;
        this.hostOrganization = hostOrganization;
        this.qualification = qualification;
        this.homepage = homepage;
        this.setCategory("Contest");
    }


}
