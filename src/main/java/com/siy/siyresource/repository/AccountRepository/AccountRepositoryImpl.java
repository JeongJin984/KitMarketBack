package com.siy.siyresource.repository.AccountRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siy.siyresource.domain.condition.AccountSearchCondition;
import com.siy.siyresource.domain.entity.account.Account;
import com.siy.siyresource.repository.AccountPostRepository.AccountPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.siy.siyresource.domain.entity.account.QAccount.account;
import static com.siy.siyresource.domain.entity.account.QAccountRole.accountRole;
import static com.siy.siyresource.domain.entity.post.QPost.post;
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
