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
    private LocalDateTime departTime;

    //요금
    private Long fare;

    @Enumerated(EnumType.STRING)
    private Gender qualifyGender;   //[MALE, FEMALE, NONE]

    @QueryProjection
    public CarPoolDto(Long id, String writer, String title, String content, LocalDateTime dueDate, LocalDateTime createdAt, Integer maxNum,
                      Integer curNum, String category, String status, String departure, String destination, LocalDateTime departTime, Long fare, Gender qualifyGender) {
        super(id, writer, title, content, dueDate, createdAt, maxNum, curNum, category, status);
        this.departure = departure;
        this.destination = destination;
        this.departTime = departTime;
        this.fare = fare;
        this.qualifyGender = qualifyGender;
    }
}
