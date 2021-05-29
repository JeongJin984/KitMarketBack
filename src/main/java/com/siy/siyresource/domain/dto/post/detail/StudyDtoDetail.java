package com.siy.siyresource.domain.dto.post.detail;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.dto.post.ApplicationDto;
import com.siy.siyresource.domain.dto.post.ParticipantsDto;
import com.siy.siyresource.domain.entity.post.Study.Study;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class StudyDtoDetail extends PostDtoDetail{
    private String study;

    @QueryProjection
    public StudyDtoDetail(Study study, Set<ParticipantsDto> participants, Set<ApplicationDto> applications){
        super(study, participants, applications);
        this.study = study.getCategory();
    }

}
