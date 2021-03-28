package com.siy.KitMarket.service.user;

import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountRegisterService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Account registerNewAccount(Account account) throws Exception {
        if (accountRepository.findByUsername(account.getUsername()) != null) {
            throw new UsernameNotFoundException(
                    "There is an account with that email adress:" + account.getUsername());
        }
        Account user = new Account(
                account.getUsername(),
                passwordEncoder.encode(account.getPassword()),
                account.getEmail(),
                account.getAge());

        return accountRepository.saveAndFlush(user);
    }

}
