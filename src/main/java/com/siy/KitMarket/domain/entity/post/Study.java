package com.siy.KitMarket.domain.entity.post;

import com.siy.KitMarket.domain.entity.account.Account;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@DiscriminatorValue("Study")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study extends Post{
    private String study;

    @Builder
    public Study(String title, String content, String study) {
        super(title, content);
        this.setCategory("study");
        this.study = study;
    }
    @Builder
    public Study(String title, String content, Account account, String study, int currentNum, int maxNum, LocalDate deadLine) {
        super(title, content, account.getUsername(), currentNum, maxNum, deadLine);
        this.setCategory("study");
        this.study = study;
    }
}
