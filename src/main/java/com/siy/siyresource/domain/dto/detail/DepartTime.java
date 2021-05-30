package com.siy.siyresource.domain.dto.detail;

import lombok.AllArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@AllArgsConstructor
public class DepartTime {
    private Long year;
    private Long month;
    private Long date;
    private Long hours;
    private Long minutes;

    public DepartTime(String time) {
    }
}
