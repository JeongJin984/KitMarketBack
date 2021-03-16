package com.siy.KitMarket.security.service;

import com.siy.KitMarket.domain.entity.account.Account;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter @Setter
public class AccountContext extends User {
    private Account account;

    public AccountContext(Account account, List<GrantedAuthority> roles) {
        super(account.getUsername(), account.getPassword(), roles);
        this.account = account;
    }
}