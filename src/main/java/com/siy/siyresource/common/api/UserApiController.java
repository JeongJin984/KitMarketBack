package com.siy.siyresource.common.api;

import com.siy.siyresource.domain.condition.AccountSearchCondition;
import com.siy.siyresource.domain.dto.account.FullAccountDto;
import com.siy.siyresource.domain.entity.account.Account;
import com.siy.siyresource.repository.AccountRepository.AccountRepository;
import com.siy.siyresource.service.user.AccountRegisterService;
import com.siy.siyresource.service.user.AccountSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
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

}
