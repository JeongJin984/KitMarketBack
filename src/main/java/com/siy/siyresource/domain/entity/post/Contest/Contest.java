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

    public ContestCategory stringToContestCategory(String category){
        if(category.equals("REPORT"))
            return ContestCategory.REPORT;
        else if(category.equals("IDEA"))
            return ContestCategory.IDEA;
        else if(category.equals("DESIGN"))
            return ContestCategory.DESIGN;
        else if(category.equals("CHARACTER"))
            return ContestCategory.CHARACTER;
        else if(category.equals("CULTURE"))
            return ContestCategory.CULTURE;
        else if(category.equals("UCC"))
            return ContestCategory.UCC;
        else if(category.equals("EXTERNAL_ACTIVITY"))
            return ContestCategory.EXTERNAL_ACTIVITY;
        return
                null;
    }

    public Qualification stringToQualification(String category){
        if(category.equals("HIGHSCHOOL"))
            return Qualification.HIGHSCHOOL;
        else if(category.equals("COLLEGE"))
            return Qualification.COLLEGE;
        else if(category.equals("NONE"))
            return Qualification.NONE;
        return null;
    }


    public Contest(@NotNull String writer, @NotNull String title, String content, @NotNull Integer maxNumber, Integer currentNumber, @NotNull LocalDateTime dueDate, String category, PostStatus postStatus, ContestCategory contestCategory, String hostOrganization, Qualification qualification, String homepage) {
        super(writer, title, content, maxNumber, currentNumber, dueDate, category, postStatus);
        this.contestCategory = contestCategory;
        this.hostOrganization = hostOrganization;
        this.qualification = qualification;
        this.homepage = homepage;
        this.setCategory("Contest");
    }


}
