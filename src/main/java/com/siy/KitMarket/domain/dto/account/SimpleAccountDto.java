package com.siy.KitMarket.domain.dto.account;

import com.querydsl.core.annotations.QueryProjection;
import com.siy.KitMarket.domain.entity.account.Account;
import lombok.Data;

@Data
public class SimpleAccountDto {
    private Long id;
    private String username;

    public SimpleAccountDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public SimpleAccountDto(Long id) {
        this.id = id;
        this.username = "";
    }

    public SimpleAccountDto(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
    }
}
