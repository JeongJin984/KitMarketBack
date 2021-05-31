package com.siy.siyresource.domain.dto.PostingDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DepartTime {
    private Long hours;
    private Long minutes;


     public static DepartTime LocalDateTimeToDepartTime(LocalDateTime time){
        DepartTime departTime = new DepartTime();
        System.out.println("time.getHour() = " + time.getHour());
        System.out.println("time.getMinute() = " + time.getMinute());

        departTime.setHours(Long.valueOf(time.getHour()));
        departTime.setMinutes(Long.valueOf(time.getMinute()));

        return departTime;
    }
}
