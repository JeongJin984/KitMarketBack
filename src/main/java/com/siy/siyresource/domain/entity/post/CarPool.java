package com.siy.siyresource.domain.entity.post;

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
public class CarPool extends Post{

    private String departure;

    private String destination;

    //출발 시간
    private LocalDateTime departureTime;

    //요금
    private Long fare;

    @Builder
    public CarPool(String title, String content) {
        super(title, content);
        this.setCategory("study");
    }

    @Builder
    public CarPool(Post post, String category) {
        super(post.getTitle(),
                post.getContent(),
                post.getWriter(),
                post.getCurrentNumber(),
                post.getMaxNumber(),
                post.getDeadLine(),
                post.getPostStatus());
        this.setCategory("study");
    }
    @Builder
    public CarPool(String title, String content, @NotNull String writer, @NotNull Integer currentNumber, @NotNull Integer maxNumber, @NotNull LocalDateTime deadLine, PostStatus status, String departure, String destination, LocalDateTime departureTime, Long fare, Gender qualifyGender) {
        super(title, content, writer, currentNumber, maxNumber, deadLine, status);
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.fare = fare;
    }


}
