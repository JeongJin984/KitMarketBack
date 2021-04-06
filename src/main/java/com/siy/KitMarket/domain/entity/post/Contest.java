package com.siy.KitMarket.domain.entity.post;


import com.siy.KitMarket.domain.entity.account.Account;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@DiscriminatorValue("Contest")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contest extends Post{
    private String Contest;

    @Builder
    public Contest(String title, String content, String Contest) {
        super(title, content);
        this.Contest = Contest;
    }

    @Builder
    public Contest(String title, String content, Account account, String Contest) {
        super(title, content, account.getUsername());
        this.Contest = Contest;
    }


}
