package com.siy.KitMarket.repository.AccountRepository;

import com.siy.KitMarket.domain.condition.AccountSearchCondition;
import com.siy.KitMarket.domain.entity.account.Account;

import java.util.List;

public interface AccountRepositoryCustom {
    Account findByUsername(String username);
    List<Account> findAccounts(AccountSearchCondition condition);
    Account findAccount(AccountSearchCondition condition);
}
