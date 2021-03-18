package com.siy.KitMarket.domain.entity.post;


import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@DiscriminatorValue("Contest")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CONTEST")
public class Contest extends Post{
    private String Contest;

    @Builder
    public Contest(String title, String content, String Contest) {
        super(title, content);
        this.Contest = Contest;
    }
}
