package com.siy.siyresource.domain.dto.PostingDetail;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.dto.ApplicationDto;
import com.siy.siyresource.domain.dto.ParticipantsDto;
import com.siy.siyresource.domain.entity.post.Contest.Contest;
import com.siy.siyresource.domain.entity.post.Contest.ContestCategory;
import com.siy.siyresource.domain.entity.post.Contest.Qualification;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Data
public class ContestDtoPostingDetail extends PostDtoPostingDetail {
    private ContestCategory destination; // 분야별 선택 [ REPORT,IDEA,DESIGN,CHARACTER,CULTURE,UCC, EXTERNAL_ACTIVITY]
    private String hostOrganization;    // 주최기간
    @Enumerated(EnumType.STRING)
    private Qualification qualification;   //자격 [HIGHSCHOOL, COLLEGE, NONE]
    private String homepage;    //주최 관련 홈페이지



    @QueryProjection
    public ContestDtoPostingDetail(Contest contest, Set<ApplicationDto> applications){
        super(contest, applications);
        setDestination(contest.getContestCategory());
        setHostOrganization(contest.getHostOrganization());
        setQualification(contest.getQualification());
        setHomepage(contest.getHomepage());
    }

}
