package com.siy.KitMarket.service.user;

import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.repository.AccountRepository.AccountRepository;
import com.siy.KitMarket.repository.AccountRepository.AccountRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountRegisterService {

    private PasswordEncoder passwordEncoder;
    private AccountRepository accountRepository;

    @Autowired
    public AccountRegisterService(PasswordEncoder passwordEncoder, AccountRepository accountRepository, AccountRepositoryImpl accountRepositoryImpl) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account registerNewAccount(Account account) throws Exception {
        if (accountRepository.findByUsername(account.getUsername()) != null) {
            throw new UsernameNotFoundException(
                    "There is an account with that email address:" + account.getUsername());
        }
        Account user = new Account(
                account.getUsername(),
                passwordEncoder.encode(account.getPassword()),
                account.getEmail(),
                account.getAge());

        return accountRepository.saveAndFlush(user);
    }

}
