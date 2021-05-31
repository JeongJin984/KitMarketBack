package com.siy.siyresource.domain.dto.PostingDetail;

import lombok.AllArgsConstructor;

import javax.persistence.Embeddable;

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
