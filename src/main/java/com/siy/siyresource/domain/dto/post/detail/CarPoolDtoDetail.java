package com.siy.siyresource.domain.dto.post.detail;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.dto.post.ApplicationDto;
import com.siy.siyresource.domain.dto.post.ParticipantsDto;
import com.siy.siyresource.domain.entity.post.CarPool;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class CarPoolDtoDetail extends PostDtoDetail{
    private String carFool;

    @QueryProjection
    public CarPoolDtoDetail(CarPool carPool, Set<ParticipantsDto> participants, Set<ApplicationDto> applications){
        super(carPool, participants, applications);
        this.carFool = carPool.getCategory();
    }




}
