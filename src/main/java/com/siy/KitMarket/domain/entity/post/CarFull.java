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
@DiscriminatorValue("CarFull")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CarFull extends Post{
    private String CarFull;

    @Builder
    public CarFull(String title, String content, String CarFull) {
        super(title, content);
        this.setCategory("carFool");
        this.CarFull = CarFull;
    }
    @Builder
    public CarFull(String title, String content, Account account, String CarFUll, int currentNum, int maxNum, LocalDate deadLine) {
        super(title, content, account.getUsername(), currentNum, maxNum, deadLine);
        this.setCategory("carFool");
        this.CarFull = CarFull;
    }
}
