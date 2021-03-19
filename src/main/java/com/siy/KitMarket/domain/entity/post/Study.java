package com.siy.KitMarket.domain.entity.post;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@DiscriminatorValue("Study")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STUDY")
public class Study extends Post{
    private String study;

    @Builder
    public Study(String title, String content, String study) {
        super(title, content);
        this.study = study;
    }
}
