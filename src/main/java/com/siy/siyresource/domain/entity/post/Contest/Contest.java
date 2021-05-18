package com.siy.siyresource.domain.entity.post.Contest;

import com.siy.siyresource.domain.entity.post.Post;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DiscriminatorValue("Contest")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contest extends Post {
    @Enumerated(EnumType.STRING)
    private ContestCategory contestCategory; // 분야별 선택 [ REPORT,IDEA,DESIGN,CHARACTER,CULTURE,UCC, EXTERNAL_ACTIVITY]

    private String hostOrganization;    // 주최기간

    @Enumerated(EnumType.STRING)
    private Qualification qualification;   //자격 [HIGHSCHOOL, COLLEGE, NONE]

    private String homepage;    //주최 관련 홈페이지

    @Builder
    public Contest(String title, String content, ContestCategory contestCategory) {
        super(title, content);
        this.setCategory("study");
        this.contestCategory = contestCategory;
    }

    @Builder
    public Contest(Post post, ContestCategory category) {
        super(post.getTitle(),
                post.getContent(),
                post.getWriter(),
                post.getCurrentNumber(),
                post.getMaxNumber(),
                post.getDeadLine());
        this.setCategory("study");
        this.contestCategory = contestCategory;
    }
    public static Contest CreateContest(){
        return new Contest();
    }

}
