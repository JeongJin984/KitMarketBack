package com.siy.KitMarket.repository;

import com.siy.KitMarket.domain.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    Account saveAndFlush(Account account);
    int countByUsername(String username);
}
