package com.siy.KitMarket.api;

import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.repository.AccountRepository.AccountRepository;
import com.siy.KitMarket.service.user.AccountRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApiController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountRegisterService accountRegisterService;

    @GetMapping(value = "/hello")
    String PostLogin(@SessionAttribute("SPRING_SECURITY_CONTEXT") SecurityContext securityContext) {
        Authentication authentication = securityContext.getAuthentication();

        Account account = (Account) authentication.getPrincipal();

        return "hello world";
    }

    @GetMapping(value = "/login")
    String login() {
        return "login";
    }

    @PostMapping(value = "/api/signup")
    String signup(@ModelAttribute Account account) throws Exception {
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
