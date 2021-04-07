package com.siy.KitMarket.api;

import com.siy.KitMarket.domain.condition.AccountSearchCondition;
import com.siy.KitMarket.domain.dto.account.AccountDto;
import com.siy.KitMarket.domain.dto.account.FullAccountDto;
import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.repository.AccountRepository.AccountRepository;
import com.siy.KitMarket.service.user.AccountRegisterService;
import com.siy.KitMarket.service.user.AccountSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApiController {

    AccountRepository accountRepository;
    AccountRegisterService accountRegisterService;
    AccountSearchService accountSearchService;

    @Autowired
    public UserApiController(
            AccountRepository accountRepository,
            AccountRegisterService accountRegisterService,
            AccountSearchService accountSearchService) {
        this.accountRepository = accountRepository;
        this.accountRegisterService = accountRegisterService;
        this.accountSearchService = accountSearchService;
    }

    @GetMapping(value = "/api/user")
    AccountDto getUser(@SessionAttribute("SPRING_SECURITY_CONTEXT") SecurityContext securityContext) {
        Authentication authentication = securityContext.getAuthentication();
        return new AccountDto((Account)authentication.getPrincipal());
    }

    @GetMapping(value = "/api/profile")
    FullAccountDto getFullProfile(
            @SessionAttribute("SPRING_SECURITY_CONTEXT") SecurityContext securityContext,
            @RequestParam(value = "offset",defaultValue = "0", required = false)int offset,
            @RequestParam(value = "size", defaultValue = "8", required = false)int size
            ) {
        Authentication authentication = securityContext.getAuthentication();
        Account account = (Account) authentication.getPrincipal();

        AccountSearchCondition condition = new AccountSearchCondition();
        condition.setUserName(account.getUsername());

        return accountSearchService.findFullAccountDto(account, condition, offset, size);
    }

    @GetMapping(value = "/api/profile/{username}")
    FullAccountDto getFullUser(
            @PathVariable(value = "username") String username,
            @RequestParam(value = "offset",defaultValue = "0", required = false)int offset,
            @RequestParam(value = "size", defaultValue = "8", required = false)int size) {
        AccountSearchCondition condition = new AccountSearchCondition();
        condition.setUserName(username);
        if(username == null) return null;
        return accountSearchService.findFullAccountDto(null, condition, offset, size);
    }

    @GetMapping(value = "/hello")
    String PostLogin(@SessionAttribute("SPRING_SECURITY_CONTEXT") SecurityContext securityContext) {
        Authentication authentication = securityContext.getAuthentication();

        Account account = (Account) authentication.getPrincipal();

        return "hello world";
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
