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
@DiscriminatorValue("Study")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study extends Post{
    public Study(String title, String content) {
        super(title, content);
    }
}
