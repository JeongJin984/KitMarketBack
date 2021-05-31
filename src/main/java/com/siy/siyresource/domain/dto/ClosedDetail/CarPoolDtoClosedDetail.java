package com.siy.siyresource.domain.dto.ClosedDetail;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.dto.DepartTime;
import com.siy.siyresource.domain.entity.post.CarPool.CarPool;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CarPoolDtoClosedDetail extends PostDtoClosedDetail{
    private String fare;
    private String departure;
    private String destination;

    @Embedded
    private DepartTime departTime;


    @QueryProjection
    public CarPoolDtoClosedDetail(CarPool carPool, Set<ParticipantsDetail> participants){
        super(carPool, participants);
        System.out.println("carPool.getDepartTime() = " + carPool.getDepartTime());

        this.fare = carPool.getFare().toString();
        this.departure = carPool.getDeparture();
        this.destination = carPool.getDestination();
        this.departTime = DepartTime.LocalDateTimeToDepartTime(carPool.getDepartTime());
    }
}
