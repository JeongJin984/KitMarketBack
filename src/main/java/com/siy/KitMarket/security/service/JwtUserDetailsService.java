package com.siy.KitMarket.security.service;

import com.siy.KitMarket.domain.entity.Account;
import com.siy.KitMarket.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service("userDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode("1111"));
        if(account == null) {
            if(accountRepository.countByUsername(username) == 0){
                throw new UsernameNotFoundException("No User Found With Username: " + username);
            }
        }

        return new AccountContext(account, new ArrayList());
    }
}
