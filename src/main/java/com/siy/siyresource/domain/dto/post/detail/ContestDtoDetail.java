package com.siy.siyresource.domain.dto.post.detail;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.dto.account.AccountDto;
import com.siy.siyresource.domain.dto.post.ApplicationDto;
import com.siy.siyresource.domain.dto.post.ParticipantsDto;
import com.siy.siyresource.domain.entity.post.CarFull;
import com.siy.siyresource.domain.entity.post.Contest;
import lombok.Data;

import java.util.Set;

@Data
public class ContestDtoDetail extends PostDtoDetail{
    private String contest;

    @QueryProjection
    public ContestDtoDetail(Contest contest, Set<ParticipantsDto> participants, Set<ApplicationDto> applications){
        super(contest, participants, applications);
        this.contest = contest.getCategory();
    }

}
