package com.siy.KitMarket.controller;

import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.repository.AccountRepository;
import com.siy.KitMarket.service.user.AccountRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountRegisterService accountRegisterService;

    @GetMapping(value = "/hello")
    String jwtLogin() {
        return "hello world";
    }

    @PostMapping(value = "/hello")
    String PostLogin() {
        return "hello world";
    }

    @GetMapping(value = "/login")
    String login() {
        return "login";
    }

    @PostMapping(value = "/api/signup")
    String signup(@RequestBody Account account) throws Exception {
        try {
            accountRegisterService.registerNewAccount(account);
        } catch (Exception e) {
            if(e instanceof UsernameNotFoundException) {
                return e.getMessage();
            }
        }
        return "redirect:/";
    }
}
