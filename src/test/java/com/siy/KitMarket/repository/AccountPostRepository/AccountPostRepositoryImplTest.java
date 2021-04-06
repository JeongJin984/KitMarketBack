package com.siy.KitMarket.repository.AccountPostRepository;

import com.siy.KitMarket.domain.condition.AccountPostSearchCondition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountPostRepositoryImplTest {

    @Autowired
    AccountPostRepository accountPostRepository;

    @Test
    void GetAccountPost() {
        AccountPostSearchCondition condition = new AccountPostSearchCondition();
        condition.setUsername("foo");
    }

}