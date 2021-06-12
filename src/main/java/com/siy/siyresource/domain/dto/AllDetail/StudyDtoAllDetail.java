package com.siy.siyresource.domain.dto.AllDetail;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.dto.ApplicationDto;
import com.siy.siyresource.domain.dto.ParticipantsDetail;
import com.siy.siyresource.domain.dto.PostingDetail.PostDtoPostingDetail;
import com.siy.siyresource.domain.entity.post.Study.Study;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class StudyDtoAllDetail extends PostDtoAllDetail {
    private String subject;

    private String region;

    private String duration;

    @QueryProjection
    public StudyDtoAllDetail(Study study, Set<ApplicationDto> applications, Set<ParticipantsDetail> participants){
        super(study, applications, participants);
        this.subject = study.getSubject().toString();
        this.region = study.getRegion();
        this.duration = study.getDuration();
    }

}