package com.siy.siyresource.repository.AccountRepository;


import com.siy.siyresource.domain.condition.AccountSearchCondition;
import com.siy.siyresource.domain.entity.account.Account;

import java.util.List;

public interface AccountRepositoryCustom {
    Account findByUsername(String username);
    List<Account> findAccounts(AccountSearchCondition condition);
    Account findAccount(AccountSearchCondition condition);
}
