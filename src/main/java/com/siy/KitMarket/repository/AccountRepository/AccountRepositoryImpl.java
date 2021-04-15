package com.siy.KitMarket.repository.AccountRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siy.KitMarket.domain.condition.AccountPostSearchCondition;
import com.siy.KitMarket.domain.condition.AccountSearchCondition;
import com.siy.KitMarket.domain.dto.account.AccountDto;
import com.siy.KitMarket.domain.dto.account.FullAccountDto;
import com.siy.KitMarket.domain.dto.account.QFullAccountDto;
import com.siy.KitMarket.domain.dto.accountPost.AccountPostDto;
import com.siy.KitMarket.domain.dto.post.PostDto;
import com.siy.KitMarket.domain.entity.account.Account;
import com.siy.KitMarket.domain.entity.account.QAccount;
import com.siy.KitMarket.domain.entity.account.QAccountRole;
import com.siy.KitMarket.repository.AccountPostRepository.AccountPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.siy.KitMarket.domain.entity.account.QAccount.account;
import static com.siy.KitMarket.domain.entity.account.QAccountRole.accountRole;
import static com.siy.KitMarket.domain.entity.post.QPost.post;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class AccountRepositoryImpl implements AccountRepositoryCustom {

    JPAQueryFactory queryFactory;
    AccountPostRepository accountPostRepository;

    @Autowired
    public AccountRepositoryImpl(JPAQueryFactory queryFactory,
                                 AccountPostRepository accountPostRepository) {
        this.queryFactory = queryFactory;
        this.accountPostRepository = accountPostRepository;
    }

    /*
    * 이름으로 Account & 연관관계 찾기
    * */
    public Account findByUsername(String name) {

        return queryFactory
                .selectFrom(account)
                .where(account.username.eq(name))
                .distinct()
                .join(account.accountRoles, accountRole).fetchJoin()
                .fetchOne();
    }

    /*
     * condition 기반 Account 탐색
     * */
    public List<Account> findAccounts(AccountSearchCondition condition) {
        return queryFactory
                .select(account)
                .where(
                        usernameEq(condition.getUserName())
                )
                .fetch();
    }

    public Account findAccount(AccountSearchCondition condition) {
        return queryFactory
                .selectFrom(account)
                .where(
                        usernameEq(condition.getUserName())
                )
                .fetchOne();
    }

    BooleanExpression usernameEq(String username) {
        return hasText(username) ? account.username.eq(username) : null;
    }


}
