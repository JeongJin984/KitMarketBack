package com.siy.KitMarket.domain.entity.post;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@DiscriminatorValue("CarFull")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CARFULL")
public class CarFull extends Post{
    private String CarFull;

    @Builder
    public CarFull(String title, String content, String CarFull) {
        super(title, content);
        this.CarFull = CarFull;
    }
}
