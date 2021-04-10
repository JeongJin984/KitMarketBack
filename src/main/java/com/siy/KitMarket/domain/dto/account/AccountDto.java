package com.siy.KitMarket.domain.dto.account;

import com.siy.KitMarket.domain.entity.account.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDto {
    private String username;
    private String email;
    private int age;

    public AccountDto(Account account) {
        this.username = account.getUsername();
        this.email = account.getEmail();
        this.age = account.getAge();
    }

    public AccountDto(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }
}
