package com.siy.siyresource.repository.AccountPostRepository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siy.siyresource.domain.condition.AccountPostSearchCondition;
import com.siy.siyresource.domain.entity.account.AccountCode;
import com.siy.siyresource.domain.entity.accountPost.AccountPost;
import com.siy.siyresource.repository.AccountPostRepository.AccountPostRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

import static com.siy.siyresource.domain.entity.accountPost.QAccountPost.accountPost;
import static com.siy.siyresource.domain.entity.account.QAccount.account;
import static com.siy.siyresource.domain.entity.post.QPost.post;

public class AccountPostRepositoryImpl implements AccountPostRepositoryCustom {

    private JPAQueryFactory queryFactory;

    @Autowired
    public AccountPostRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<AccountPost> findAccountPost(AccountPostSearchCondition condition, Pageable pageable) {
        List<AccountPost> list = queryFactory
                .selectFrom(accountPost)
                .join(accountPost.post(), post).fetchJoin()
                .join(accountPost.account(), account).fetchJoin()
                .distinct()
                .where(
                        usernameEq(condition.getUsername()),
                        postEq(condition.getPostId()),
                        accountCodeEq(condition.getCode())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<AccountPost> countQuery = queryFactory
                .selectFrom(accountPost);

        return PageableExecutionUtils.getPage(list, pageable, countQuery::fetchCount);
    }

    @Override
    public Set<String> findParticipant(AccountPostSearchCondition condition) {
        List<AccountPost> list = queryFactory
                .selectFrom(accountPost)
                .where(
                        postEq(condition.getPostId()),
                        accountCodeEq(condition.getCode())
                )
                .join(accountPost.post(), post).fetchJoin()
                .fetch();

        return list.stream().map(AccountPost::getUsername).collect(Collectors.toSet());
    }


    /*
    * BooleanExpressions
    * */
    private BooleanExpression usernameEq(String username) {
        return hasText(username) ? accountPost.username.eq(username) : null;
    }

    private BooleanExpression postEq(Long id) {
        return id == null ? null : accountPost.post().id.eq(id);
    }

    private BooleanExpression accountCodeEq(AccountCode code) {
        return code == null ? null : accountPost.code.eq(code) ;
    }
}
