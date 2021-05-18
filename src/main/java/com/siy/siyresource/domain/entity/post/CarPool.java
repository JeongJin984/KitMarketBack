package com.siy.siyresource.domain.entity.post;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DiscriminatorValue("CarPool")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
                post.getDeadLine());
        this.setCategory("study");
    }

    public static CarPool CreateCarFool() {
        return new CarPool();
    }
}
