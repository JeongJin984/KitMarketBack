package com.siy.KitMarket.repository.AccountRepository;

import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.repository.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CommonRepository<Account, Long>, AccountRepositoryCustom {
}