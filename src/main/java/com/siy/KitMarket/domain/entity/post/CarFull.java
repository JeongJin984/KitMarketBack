package com.siy.KitMarket.domain.entity.post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("CarFull")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CarFull extends Post{
    public CarFull(String title, String content) {
        super(title, content);
    }
}
