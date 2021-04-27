package com.siy.siyresource.repository.AccountRepository;


import com.siy.siyresource.domain.entity.account.Account;
import com.siy.siyresource.repository.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CommonRepository<Account, Long>, AccountRepositoryCustom {
}