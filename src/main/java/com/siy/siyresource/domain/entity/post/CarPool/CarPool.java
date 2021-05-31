package com.siy.siyresource.domain.entity.post.CarPool;

import com.siy.siyresource.domain.entity.post.Gender;
import com.siy.siyresource.domain.entity.post.Post;
import com.siy.siyresource.domain.entity.post.PostStatus;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DiscriminatorValue("CarPool")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CarPool extends Post {

    private String departure;

    private String destination;

    //출발 시간
    private LocalDateTime departureTime;

    //요금
    private Long fare;

    public CarPool(@NotNull String writer, @NotNull String title, String content, @NotNull Integer maxNumber, Integer currentNumber, @NotNull LocalDateTime dueDate, String category, PostStatus postStatus, Gender qualifyGender, String departure, String destination, LocalDateTime departureTime, Long fare) {
        super(writer, title, content, maxNumber, currentNumber, dueDate, category, postStatus, qualifyGender);
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.fare = fare;
        this.setCategory("CarPool");
    }
}
