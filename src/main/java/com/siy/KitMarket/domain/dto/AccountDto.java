package com.siy.KitMarket.domain.dto;

import com.siy.KitMarket.domain.entity.account.Account;
import lombok.Data;

@Data
public class AccountDto {
    private String username;
    private String email;
    private int age;

    public AccountDto(Account account) {
        this.username = account.getUsername();
        this.email = account.getEmail();
        this.age = account.getAge();
    }
}
