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

    @Enumerated(EnumType.STRING)
    private Gender qualifyGender;   //[MALE, FEMALE, NONE]

    private String departure;

    private String destination;

    //출발 시간
    private Long departHours;
    private Long departMinutes;

    //요금
    private Long fare;

    private Double Long_;
    private Double lat;

    public Gender stringToGender(String gender){
        if(gender.equals("MALE"))
            return Gender.MALE;
        else if(gender.equals("FEMALE"))
            return Gender.FEMALE;
        else if(gender.equals("NONE"))
            return Gender.NONE;
        else
            return null;
    }

    public CarPool(@NotNull String writer, @NotNull String title, String content, @NotNull Integer maxNumber, Integer currentNumber, @NotNull LocalDateTime dueDate, String category,
                   PostStatus postStatus, Gender qualifyGender, String departure, String destination, Long departHours,
                   Long departMinutes, Long fare, Double Long_, Double lat) {
        super(writer, title, content, maxNumber, currentNumber, dueDate, category, postStatus);
        this.qualifyGender = qualifyGender;
        this.departure = departure;
        this.destination = destination;
        this.departHours = departHours;
        this.departMinutes = departMinutes;
        this.fare = fare;
        this.Long_ = Long_;
        this.lat = lat;
        this.setCategory(category);
    }
}
