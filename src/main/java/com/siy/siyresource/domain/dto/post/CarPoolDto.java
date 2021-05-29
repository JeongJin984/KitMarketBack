package com.siy.siyresource.domain.dto.post;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.siyresource.domain.entity.post.Gender;
import com.siy.siyresource.domain.entity.post.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class CarPoolDto extends PostDto{
    private String departure;

    private String destination;

    //출발 시간
    private LocalDateTime departureTime;

    //요금
    private Long fare;

    @Enumerated(EnumType.STRING)
    private Gender qualifyGender;   //[MALE, FEMALE, NONE]

    @QueryProjection
    public CarPoolDto(Long id, String writer, String title, String content, LocalDateTime createdAt,
                      Integer maxNum, Integer curNum, LocalDateTime deadLine, String category) {
        super(id, writer, title, content, createdAt, maxNum, curNum, deadLine, category);
    }

    @QueryProjection
    public CarPoolDto(Long id, String writer, String title, String content, LocalDateTime createdAt, Integer maxNum, Integer curNum, LocalDateTime deadLine, String category, String departure, String destination, LocalDateTime departureTime, Long fare, Gender qualifyGender) {
        super(id, writer, title, content, createdAt, maxNum, curNum, deadLine, category);
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.fare = fare;
        this.qualifyGender = qualifyGender;
    }
}
