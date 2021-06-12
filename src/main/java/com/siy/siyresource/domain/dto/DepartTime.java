package com.siy.siyresource.domain.dto;

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



    public static DepartTime LocalDateTimeToDepartTime(Long departHours, Long departMinutes) {
        DepartTime departTime = new DepartTime();
        System.out.println("time.getHour() = " + departHours);
        System.out.println("time.getMinute() = " +  departMinutes);

        departTime.setHours(departHours);
        departTime.setMinutes(departMinutes);

        return departTime;
    }
}
