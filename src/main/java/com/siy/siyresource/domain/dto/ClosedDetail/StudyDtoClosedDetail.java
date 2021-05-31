package com.siy.siyresource.domain.dto.ClosedDetail;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.entity.post.Study.Study;
import com.siy.siyresource.domain.entity.post.Study.StudyCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class StudyDtoClosedDetail extends  PostDtoClosedDetail{
    private StudyCategory subject;
    private String region;
    private String duration;

    @QueryProjection
    public StudyDtoClosedDetail(Study study, Set<ParticipantsDetail> participants){
        super(study, participants);
        this.subject = study.getSubject();
        this.region = study.getRegion();
        this.duration = study.getDuration();
    }
}
