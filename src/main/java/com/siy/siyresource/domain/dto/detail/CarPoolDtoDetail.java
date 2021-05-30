package com.siy.siyresource.domain.dto.detail;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.dto.ApplicationDto;
import com.siy.siyresource.domain.dto.ParticipantsDto;
import com.siy.siyresource.domain.entity.post.CarPool.CarPool;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import java.util.Set;

@Data
@NoArgsConstructor
public class CarPoolDtoDetail extends PostDtoDetail{
    private String fare;
    private String departure;
    private String destination;
    @Embedded
    private DepartTime departTime;

    @QueryProjection
    public CarPoolDtoDetail(CarPool carPool, Set<ParticipantsDto> participants, Set<ApplicationDto> applications){
        super(carPool, participants, applications);
        this.fare = carPool.getFare().toString();
        this.departure = carPool.getDeparture();
        this.destination = carPool.getDestination();

    }




}
