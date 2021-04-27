package com.siy.siyresource.service.user;

import com.siy.siyresource.domain.entity.account.Account;
import com.siy.siyresource.repository.AccountRepository.AccountRepository;
import com.siy.siyresource.repository.AccountRepository.AccountRepositoryImpl;
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
    public AccountRegisterService(PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
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
