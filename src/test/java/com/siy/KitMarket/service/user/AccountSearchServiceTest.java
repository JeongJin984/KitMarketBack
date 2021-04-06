package com.siy.KitMarket.service.user;

import com.siy.KitMarket.domain.condition.AccountSearchCondition;
import com.siy.KitMarket.domain.dto.account.FullAccountDto;
import com.siy.KitMarket.domain.entity.account.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountSearchServiceTest {

    @Autowired
    AccountSearchService accountSearchService;

    @Test
    void AccountServiceTest() {
        AccountSearchCondition condition = new AccountSearchCondition();
        condition.setUserName("user");

        FullAccountDto fullAccountDto = accountSearchService.findFullAccountDto(null, condition, 0, 5);
        System.out.println(fullAccountDto.toString());
    }

}