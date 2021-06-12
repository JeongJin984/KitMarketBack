package com.siy.siyresource.domain.dto.ClosedDetail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.dto.DepartTime;
import com.siy.siyresource.domain.dto.ParticipantsDetail;
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
    @JsonProperty("long")
    private Double Long;
    private Double lat;

    @QueryProjection
    public CarPoolDtoClosedDetail(CarPool carPool, Set<ParticipantsDetail> participants){
        super(carPool, participants);

        this.fare = carPool.getFare().toString();
        this.departure = carPool.getDeparture();
        this.destination = carPool.getDestination();
        this.departTime = DepartTime.LocalDateTimeToDepartTime(carPool.getDepartHours(), carPool.getDepartMinutes());
        this.Long = carPool.getLong_();
        this.lat = carPool.getLat();
    }
}
