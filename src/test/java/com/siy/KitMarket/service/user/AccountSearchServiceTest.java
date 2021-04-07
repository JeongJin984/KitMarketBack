package com.siy.KitMarket.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    ObjectMapper mapper;

    @Test
    void AccountServiceTest() throws JsonProcessingException {
        AccountSearchCondition condition = new AccountSearchCondition();
        condition.setUserName("user");

        FullAccountDto fullAccountDto = accountSearchService.findFullAccountDto(null, condition, 0, 5);
        System.out.println(mapper.writeValueAsString(fullAccountDto));
    }

}