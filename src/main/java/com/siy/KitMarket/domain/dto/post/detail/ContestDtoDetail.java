package com.siy.KitMarket.domain.dto.post.detail;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.KitMarket.domain.dto.account.AccountDto;
import com.siy.KitMarket.domain.dto.post.ApplicationDto;
import com.siy.KitMarket.domain.dto.post.ParticipantsDto;
import com.siy.KitMarket.domain.entity.post.CarFull;
import com.siy.KitMarket.domain.entity.post.Contest;
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
