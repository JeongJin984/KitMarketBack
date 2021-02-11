package com.siy.KitMarket.repository;

import com.siy.KitMarket.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    int countByUsername(String username);
}
