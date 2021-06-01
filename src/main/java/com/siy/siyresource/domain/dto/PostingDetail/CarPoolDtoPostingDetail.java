package com.siy.siyresource.domain.dto.PostingDetail;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.dto.DepartTime;
import com.siy.siyresource.domain.entity.post.CarPool.CarPool;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import java.util.Set;

@Data
@NoArgsConstructor
public class CarPoolDtoPostingDetail extends PostDtoPostingDetail {
    private String Gender;
    private String fare;
    private String departure;
    private String destination;

    @Embedded
    private DepartTime departTime;


    @QueryProjection
    public CarPoolDtoPostingDetail(CarPool carPool, Set<ApplicationDto> applications){
        super(carPool, applications);
        System.out.println("carPool.getDepartTime() = " + carPool.getDepartTime());

        this.Gender = carPool.getQualifyGender().toString();
        this.fare = carPool.getFare().toString();
        this.departure = carPool.getDeparture();
        this.destination = carPool.getDestination();
        this.departTime = DepartTime.LocalDateTimeToDepartTime(carPool.getDepartTime());
    }

}
