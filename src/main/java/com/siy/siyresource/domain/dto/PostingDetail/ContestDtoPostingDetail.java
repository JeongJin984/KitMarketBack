package com.siy.siyresource.domain.dto.PostingDetail;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.dto.ApplicationDto;
import com.siy.siyresource.domain.entity.post.Contest.Contest;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Data
public class ContestDtoPostingDetail extends PostDtoPostingDetail {
    private String contestCategory; // 분야별 선택 [ REPORT,IDEA,DESIGN,CHARACTER,CULTURE,UCC, EXTERNAL_ACTIVITY]
    private String hostOrganization;    // 주최기간
    @Enumerated(EnumType.STRING)
    private String qualification;   //자격 [HIGHSCHOOL, COLLEGE, NONE]
    private String homepage;    //주최 관련 홈페이지



    @QueryProjection
    public ContestDtoPostingDetail(Contest contest, Set<ApplicationDto> applications){
        super(contest, applications);
        this.contestCategory = contest.getContestCategory().toString();
        this.hostOrganization = contest.getHostOrganization();
        this.qualification = contest.getQualification().toString();
        this.homepage = contest.getHomepage();
    }

}
