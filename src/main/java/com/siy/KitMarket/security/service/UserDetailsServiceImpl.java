package com.siy.KitMarket.security.service;

import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.repository.QAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private QAccountRepository qAccountRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = qAccountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        Set<String> userRoles = account.getAccountRoles()
                .stream()
                .map(accountRole -> accountRole.getRole().getRoleName())
                .collect(Collectors.toSet());

        List<GrantedAuthority> collect = userRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new AccountContext(account, collect);
    }
}
