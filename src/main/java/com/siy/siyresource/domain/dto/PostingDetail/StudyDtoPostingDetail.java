package com.siy.siyresource.domain.dto.PostingDetail;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.dto.ApplicationDto;
import com.siy.siyresource.domain.dto.ParticipantsDto;
import com.siy.siyresource.domain.entity.post.Study.Study;
import com.siy.siyresource.domain.entity.post.Study.StudyCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class StudyDtoPostingDetail extends PostDtoPostingDetail {
    private StudyCategory subject;

    private String region;

    private String duration;

    @QueryProjection
    public StudyDtoPostingDetail(Study study, Set<ApplicationDto> applications){
        super(study, applications);
        this.subject = study.getSubject();
        this.region = study.getRegion();
        this.duration = study.getDuration();
    }

}
