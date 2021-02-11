package com.siy.KitMarket.security.service;

import com.siy.KitMarket.domain.entity.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter @Setter
public class AccountContext extends User {

    private Account account;

    public AccountContext(Account account, List<? extends GrantedAuthority> roles) {
        super(account.getUsername(), account.getPassword(), roles);
        this.account = account;
    }
}
