package com.siy.siyresource.domain.dto.AllDetail;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.dto.ApplicationDto;
import com.siy.siyresource.domain.dto.ParticipantsDetail;
import com.siy.siyresource.domain.dto.ClosedDetail.PostDtoClosedDetail;
import com.siy.siyresource.domain.entity.post.Contest.Contest;
import com.siy.siyresource.domain.entity.post.Contest.ContestCategory;
import com.siy.siyresource.domain.entity.post.Contest.Qualification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ContestDtoAllDetail extends PostDtoAllDetail {
    private ContestCategory destination; // 분야별 선택 [ REPORT,IDEA,DESIGN,CHARACTER,CULTURE,UCC, EXTERNAL_ACTIVITY]
    private String hostOrganization;    // 주최기간
    @Enumerated(EnumType.STRING)
    private Qualification qualification;   //자격 [HIGHSCHOOL, COLLEGE, NONE]
    private String homepage;    //주최 관련 홈페이지



    @QueryProjection
    public ContestDtoAllDetail(Contest contest, Set<ApplicationDto> application, Set<ParticipantsDetail> participants){
        super(contest, application, participants);
        setDestination(contest.getContestCategory());
        setHostOrganization(contest.getHostOrganization());
        setQualification(contest.getQualification());
        setHomepage(contest.getHomepage());
    }


}