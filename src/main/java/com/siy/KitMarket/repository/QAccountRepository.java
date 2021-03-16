package com.siy.KitMarket.repository;

import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.domain.entity.account.QAccount;
import com.siy.KitMarket.domain.entity.account.QAccountRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class QAccountRepository {

    @Autowired
    JPAQueryFactory queryFactory;

    public Account findByUsername(String name) {
        QAccount account = new QAccount("a");
        QAccountRole accountRole = new QAccountRole("ar");

        return queryFactory
                .selectFrom(account)
                .where(account.username.eq(name))
                .join(account.accountRoles, accountRole).fetchJoin()
                .fetchOne();
    }
}
