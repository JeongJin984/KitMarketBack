package com.siy.KitMarket.repository.AccountRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siy.KitMarket.domain.condition.AccountSearchCondition;
import com.siy.KitMarket.domain.dto.account.FullAccountDto;
import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.domain.entity.account.QAccount;
import com.siy.KitMarket.domain.entity.post.Post;
import lombok.val;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.siy.KitMarket.domain.entity.account.QAccountRole.accountRole;
import static org.junit.jupiter.api.Assertions.*;

import static com.siy.KitMarket.domain.entity.account.QAccount.account;
import static com.siy.KitMarket.domain.entity.post.QPost.post;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    JPAQueryFactory queryFactory;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void AccountRepositoryTest() throws JsonProcessingException {

        Account acc = queryFactory
                .selectFrom(account)
                .where(account.username.eq("user"))
                .fetchOne();

        System.out.println(mapper.writeValueAsString(acc));
    }


}